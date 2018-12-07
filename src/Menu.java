import java.util.*;

/**
 * Skakklubben K41's menu.
 * Herfra kan man tilgaa de forskellige funktioner i programmet.
 *
 * @author Gruppe1
 * @version 1.0
 * @since 07-12-2018
 */

public class Menu
{

    private Scanner console;

    /**
     * Konstruktor til menu.
     */

    public Menu()
    {
        console = new Scanner(System.in);
    }


    /**
     * Metoden korerProgram er en metode til at koere menuen.
     */

    public void korerProgram()
    {
        boolean runProgram = true;

        System.out.println("Velkommen!");
        System.out.println("Hvad oensker du at foretage dig?");

        while (runProgram)
        {
            System.out.println("1: Medlemskab");
            System.out.println("2: Kontingent");
            System.out.println("0: Afslut");

            char valg = console.next().charAt(0);
            String dummy = console.nextLine(); //læs enter indtasten


            if (valg == '0')
            {
                System.out.println("Afslutter ...");
                runProgram = false;
            } else if (valg == '1')
            {
                Medlemskab medlemskab = new Medlemskab();

                boolean undermenuValg1 = true;
                while (undermenuValg1)
                {
                    visMedlemskabsMenu();

                    char valg1 = console.next().charAt(0);
                    dummy = console.nextLine();

                    if (valg1 == '0')
                    {
                        System.out.println("Afslutter ...");
                        undermenuValg1 = false;
                    } else if (valg1 == '1')
                    {
                        System.out.println("Du har valgt at oprette et nyt medlem.");
                        System.out.println("(Indtast '\\N' hvis programmet forespørger ukendt data)");
                        medlemskab.gemMedlem();
                        undermenuValg1 = false;
                    } else if (valg1 == '2')
                    {
                        System.out.println("Du har valgt at opdatere et medlems oplysninger.");
                        System.out.println("Skriv medlemsnummer:");

                        while (!console.hasNextInt())
                        {
                            console.nextLine();
                            System.out.println("Skriv medlemsnummer:");
                        }

                        int nummer = console.nextInt();
                        dummy = console.nextLine();
                        medlemskab.opdatereMedlem(nummer);
                        undermenuValg1 = false;
                    }


                }
            } else if (valg == '2')
            {
                boolean undermenuValg2 = true;
                while (undermenuValg2)
                {
                    visKontingenthaandteringMenu();

                    char valg2 = console.next().charAt(0);
                    dummy = console.nextLine();

                    if (valg2 == '0')
                    {
                        System.out.println("Afslutter ...");
                        undermenuValg2 = false;
                    } else if (valg2 == '1')
                    {
                        String[] priser = Filhaandtering.laesPriser();
                        for (String pris : priser)
                        {
                            System.out.println(pris);
                        }
                        undermenuValg2 = false;
                    } else if (valg2 == '2')
                    {
                        Filhaandtering.laesRestanceListe();
                        undermenuValg2 = false;
                    }
                }

            }
        }
    }


            /**
             * visMedlemskabsmenu printer teksten til medlemskabsmenuen.
             */

            public void visMedlemskabsMenu ()
            {
                System.out.println("Du har valgt medlemskab.");
                System.out.println("Hvad Oensker du at foretage dig?");
                System.out.println("1: Oprette et nyt medlem");
                System.out.println("2: Opdatere oplysninger paa et eksisterende medlem");
                System.out.println("0: Afslut");

            }

            /**
             * visKontingenthaandteringMenu printer teksten til kontingenthaandteringsmenuen.
             */

            public void visKontingenthaandteringMenu ()
            {
                System.out.println("Du har valgt kontingent.");
                System.out.println("Hvad oensker du at foretage dig?");
                System.out.println("1: Se priser");
                System.out.println("2: Se medlemmer i restance");
                System.out.println("0: Afslut");

            }
        }