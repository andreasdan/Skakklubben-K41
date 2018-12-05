import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Denne statiske klasse har ansvaret for at læse/skrive til filerne i systemet.
 *
 * @author   Gruppe1
 * @version  1.0
 * @since    07-12-2018
 */

public final class Filhaandtering
{
    /**
     * Variabler som hvert indeholder filnavnet som skal tilgås
     */
    private static String kontingentFilnavn = "Kontingenttyper.txt";
    private static String medlemsFilnavn = "Medlemmer.txt";
    private static String juniorFilnavn = "CPR.txt";
    private static String denSorteListe = "DenSorteListe.txt";

    private Filhaandtering()
    {

    }

    /**
     * Metode som læser kontingenttyper og priser fra kontingent fil
     * Der tilføjes automatisk en senoir60+ som har givet 25% til den almindelige senior pris
     *
     * @return String[] liste af kontingenttype med tilhørende priser klar til udprint.
     */
    public static String[] laesPriser()
    {
        //array med mulige priser i filen
        String[] priser = new String[]{"", "", "", ""};

        try
        {
            File file = new File(kontingentFilnavn);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke læses. Priserne kunne ikke returneres");
            }
            else
            {
                Scanner scanner = new Scanner(file, "UTF-8");
                int i = 0; //index
                int kontingenttype; //pris læst direkte fra fil
                String top = scanner.nextLine(); //første linje skal ikke læses
                while (scanner.hasNextLine())
                {
                    kontingenttype = scanner.nextInt();
                    if (kontingenttype == 0)
                    {
                        priser[i] = "Junior\t\t: " + scanner.nextLine() + " kr.";
                    }
                    else if (kontingenttype == 1)
                    {
                        int seniorPris = Integer.parseInt(scanner.nextLine().replace("\t", "")); //laeser hele linjen plus tab men skal kun laese prisen

                        priser[i] = "Senior\t\t: " + "\t" + seniorPris + " kr.";

                        i++; //næste index så vi ikke overskriver med senior60+ for almindelig senior
                        priser[i] = "Senior60+\t: " + "\t" + (seniorPris * 0.75) + " kr.";
                    }
                    else if (kontingenttype == 2)
                    {
                        priser[i] = "Passiv:\t\t" + scanner.nextLine() + " kr.";
                    }
                    else
                    {
                        priser[i] = "Ukendt pris\t\t: " + scanner.nextLine() + " kr.";
                    }

                    i++;
                }
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return priser;

    }

    /**
     * Metode til at hente antallet af medlemmer i systemet så næste medlemsnummer er kendt.
     *
     * @return int Returnere antallet af medlemmer i systemet
     */
    public static int laesAntalMedlemmer() //bruges i medlemskab til at hente næste medlemsnummer
    {
        try
        {
            File file = new File(medlemsFilnavn);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke læses.");
            }
            else
            {
                Scanner scanner = new Scanner(file, "UTF-8");
                int antal = -1; //-1 fordi toplinjen tælles med som ikke skal læses (vi begynder med 1 for første medlem)
                String linje;
                while (scanner.hasNextLine())
                {
                    linje = scanner.nextLine();
                    antal++;
                }

                return antal;
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return -1;
    }

    /**
     * Denne medtode gemmer et nyt oprettet medlem i filerne
     *
     * @param medlem Medlemmsobjekt som indeholder oplysningerne til at skrive medlem til fil
     * @param cpr CPR nummer på medlem, hvis cpr = \N gemmes det ikke
     */
    public static void gemNytMedlem(Medlem medlem, String cpr)
    {
        if (tjekSortListe(medlem.getFornavn(), medlem.getEfternavn(), medlem.getKontakt().getTelefonnummer()))
        {
            System.out.println("Medlemmet står allerede i den sorte liste. Brugeren blev ikke oprettet.");
            return; //exit void
        }

        if (tjekMedlemsListe(medlem.getFornavn(), medlem.getEfternavn(), medlem.getKontakt().getTelefonnummer()))
        {
            System.out.println("Brugeren blev ikke oprettet.");
            return; //exit void
        }

        try
        {
            File file = new File(medlemsFilnavn);
            if (file.canWrite())
            {
                // skriv medlem til filen
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                printWriter.println(medlem.tilFil());
                printWriter.flush();
                printWriter.close();

                System.out.println(medlem.toString() + " blev oprettet i systemet.");

                if (!cpr.equals("\\N"))
                {
                    File f = new File(juniorFilnavn);
                    if (!f.canWrite())
                    {
                        System.out.println("Kunne ikke gemme CPR oplysninger. Kan ikke skrive til filen.");
                    }
                    else
                    {
                        FileWriter w = new FileWriter(f, true);
                        PrintWriter pwriter = new PrintWriter(w);
                        pwriter.println(medlem.getMedlemsnummer() + "\t" + cpr);
                        pwriter.flush();
                        pwriter.close();

                        System.out.println("CPR oplysninger for junior medlem blev gemt.");
                    }
                }
            }
            else
            {
                System.out.println("Kunne ikke skrive til filen. Oplysningerne blev ikke gemt.");
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

    }

    /**
     * Denne metode ændrer en specifik oplysning for et medlem i systemets filer
     *
     * @param medlemsnummer Medlemsnummeret på medlemmet som har oplysninger der skal opdateres
     * @param kolonne 0-baseret index som referer til kolonnen i medlemsfilen der skal ændres
     * @param nyInfo Den nye data som skal skrives til filen på den specifikke kolonne
     */
    public static void gemOpdateretMedlem(int medlemsnummer, int kolonne, String nyInfo)
    {
        boolean medlemFundet = false;
        try
        {
            File file = new File(medlemsFilnavn);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke laeses. Oplysningerne blev ikke gemt i systemet.");
                return;
            }

            //læs gamle medlemmer i ny liste
            ArrayList<String> nyListe = new ArrayList<String>();
            Scanner scanner = new Scanner(file, "UTF-8");
            String top = scanner.nextLine();
            int m_nummer; //læser fra scanneren
            while (scanner.hasNextLine()) //kører så længe der er linjer at læse
            {
                m_nummer = scanner.nextInt();
                //gem gamle medlemmer der ikke skal opdateres
                if (m_nummer != medlemsnummer)
                {
                    nyListe.add(m_nummer + scanner.nextLine());
                }
                else
                {
                    medlemFundet = true;
                    String[] medlem = scanner.nextLine().split("\t");
                    medlem[kolonne] = nyInfo;
                    medlem[0] = Integer.toString(m_nummer);

                    String opdateretMedlem = medlem[0]; //loesning på klassisk stakit problem
                    for (int i = 1; i < medlem.length; i++)
                    {
                        opdateretMedlem += "\t" + medlem[i];
                    }

                    nyListe.add(opdateretMedlem);
                }
            }

            if (file.canWrite())
            {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);

                printWriter.println(top);

                //skriv gamle medlemmer til filen
                for (String s : nyListe)
                {
                    printWriter.println(s);
                }

                printWriter.flush();
                printWriter.close();
                
                if (!medlemFundet)
                {
                  System.out.println("Medlemsnummeret blev ikke fundet i filen.\n"); 
                }
                else
                {
                  System.out.println("Medlemsoplysningerne blev gemt.\n");
                }
            } else
            {
                System.out.println("Kunne ikke skrive til filen. Oplysningerne blev ikke gemt.");
            }

        } catch (IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Tjek hvorvidt oplysninger stemmer overens med et medlem i den sorte liste fil
     *
     * @param fornavn Fornavnet for medlemmet som skal tjekkes
     * @param efternavn Efternavn for medlemmet som skal tjekkes
     * @param telefonnummer Telefon nummer for medlemmet som skal tjekkes
     * @return boolean Boolean der repræsentere om medlemmet blev fundet i den sorte liste
     */
    public static boolean tjekSortListe(String fornavn, String efternavn, String telefonnummer)
    {
        try
        {
            File file = new File(denSorteListe);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke laeses. Oplysningerne blev ikke gemt i systemet.");
                return false;
            }

            //læs hvert linje i filen
            Scanner scanner = new Scanner(file, "UTF-8");
            String[] line;
            while (scanner.hasNextLine())
            {
                //tjek hvert linje om den stemmer overens med medlemsoplysningerne
                line = scanner.nextLine().split("\t");
                if (line[0].equals(fornavn) && line[1].equals(efternavn) && line[2].equals(telefonnummer))
                {
                    return true;
                }
            }

        } catch (IOException e)
        {
            System.out.println(e);
        }

        return false;
    }

    /**
     * Tjek hvorvidt oplysninger stemmer overens med et medlem i medlemsfilen (om medlemmet findes i forvejen)
     *
     * @param fornavn Fornavnet for medlemmet som skal tjekkes
     * @param efternavn Efternavn for medlemmet som skal tjekkes
     * @param telefonnummer Telefon nummer for medlemmet som skal tjekkes
     * @return boolean Boolean der repræsentere om medlemmet allerede er oprettet
     */
    public static boolean tjekMedlemsListe(String fornavn, String efternavn, String telefonnummer)
    {
        try
        {
            File file = new File(medlemsFilnavn);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke laeses. Oplysningerne blev ikke gemt i systemet.");
                return false;
            }

            //læs hvert linje i filen
            Scanner scanner = new Scanner(file, "UTF-8");
            String[] line;
            while (scanner.hasNextLine())
            {
                //tjek hvert linje om den stemmer overens med medlemsoplysningerne
                line = scanner.nextLine().split("\t");
                if (line[2].equals(fornavn) && line[3].equals(efternavn) && line[11].equals(telefonnummer))
                {
                    System.out.println("Medlemmet findes allerede:\nMedlemsnummer: " + line[0] + "\nFornavn: " + line[2] + "\nEfternavn: " + line[3]);
                    return true;
                }
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return false;
    }

    /**
     * Metode til at printe alle medlemmer der ikke har betalt til en fil
     */
    public static void laesRestanceListe()
    {
        try
        {
            File file = new File(medlemsFilnavn);

            if (!file.canRead())
            {
                System.out.println("Filen kunne ikke laeses. Vi kan ikke udprinte restanceListe.");
                return;
            }

            Scanner scanner = new Scanner(file, "UTF-8");
            String[] linje;

            File restanceListe = new File("RestanceListe");
            PrintStream tilFil = new PrintStream(restanceListe);
            String medlemsnr = "";
            String fornavn1 = "";
            String efternavn1 = "";

            while (scanner.hasNextLine())
            {
                linje = scanner.nextLine().split("\t");

                if (linje[13].equals("0"))
                {
                    medlemsnr = linje[0];
                    fornavn1 = linje[2];
                    efternavn1 = linje[3];
                    tilFil.println(medlemsnr + "\t" + fornavn1 + "\t" + efternavn1);
                }
            }
            System.out.println("Hvis der er medlemmer i restance, har vi udprintet en restanceliste.\n");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }


}
