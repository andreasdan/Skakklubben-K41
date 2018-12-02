import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class Filhaandtering
{
      private static String kontingentFilnavn = "Kontingenttyper.txt";
      private static String medlemsFilnavn = "Medlemmer.txt";
      private static String juniorFilnavn = "CPR.txt";
      private static String denSorteListe = "DenSorteListe.txt";

	private Filhaandtering()
   {
	}
   
   public static String[] laesPriser()
   {
      //rækker/linjer i filen
      String[] priser = new String[] { "", "", "", "" };
   
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
            int i = 0;
            int pris; //pris læst direkte fra fil
            String type; //pris omskrevet til tekst (0 = junior)
            String top = scanner.nextLine(); //første linje skal ikke læses
            while (scanner.hasNextLine())
            {
               pris = scanner.nextInt();
               if (pris == 0)
               {
                  type = "Junior";
               }
               else if (pris == 1)
               {
                  type = "Senior";
               }
               else if (pris == 2)
               {
                  type = "Passiv";
               }
               else
               {
                  type = "Ukendt pris";
               }
               
               priser[i] = type + ": " + scanner.nextLine() + " kr.";
               
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
   
   public static int laesAntalMedlemmer()
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
            String s;
            while (scanner.hasNextLine())
            {
               antal++;
               s = scanner.nextLine();
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

	public static KontingentInfo laesKontingentFil(int medlemsnummer)
   {
      KontingentInfo kontingent = new KontingentInfo();
   
		try
      {
         File file = new File(medlemsFilnavn);
         
         if (!file.canRead())
         {
            System.out.println("Filen kunne ikke læses. Kontingentinfo blev ikke oprettet.");
         }
         else
         {
            Scanner scanner = new Scanner(file, "UTF-8");
            int m_nummer;
            while (scanner.hasNextLine())
            {
               m_nummer = scanner.nextInt();
               if (m_nummer == medlemsnummer)
               {
                  String[] line = scanner.nextLine().split("\t");
                  
                  kontingent.setMedlemsnummer(medlemsnummer);
                  kontingent.setKontingenttype(line[9]);
                  
                  //
                  
                  if (line[13].equals("0"))
                  {
                     kontingent.setHarBetalt(false);
                  }
                  else
                  {
                     kontingent.setHarBetalt(true);
                  }
               }
            }
         }
      }
      catch (IOException e)
      {
         System.out.println(e);
      }
      
      return kontingent;
	}

	public static void gemNytMedlem(Medlem medlem, String cpr)
   {	
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
   
	public static void gemOpdateretMedlem(int medlemsnummer, int kolonne, String nyInfo)
   {
		try
      {
         File file = new File(medlemsFilnavn);
         
         if (!file.canRead())
         {
            System.out.println("Filen kunne ikke læses. Oplysningerne blev ikke gemt i systemet.");
            return;
         }
         
         //læs gamle medlemmer i ny liste
         ArrayList<String> nyListe = new ArrayList<String>();
         Scanner scanner = new Scanner(file, "UTF-8");
         int m_nummer;
         while (scanner.hasNextLine())
         {
            m_nummer = scanner.nextInt();
            //gem gamle medlemmer der ikke skal opdateres
            if (m_nummer != medlemsnummer)
            {
               nyListe.add(m_nummer + scanner.nextLine());
            }
            else
            {
            	String[] medlem = scanner.nextLine().split("\t");
            	medlem[kolonne] = nyInfo;
            	medlem[0] = Integer.toString(m_nummer);

            	String opdateretMedlem = medlem[0]; //løsning på klassisk stakit problem
            	for (int i = 1; i < medlem.length; i++)
            	{
            		opdateretMedlem += "\t" + medlem[i];
            	}

            	nyListe.add(opdateretMedlem);
            }
         }
         
         if (file.canWrite())
         {
            //clear filen
            file.delete();
            file.createNewFile();
            
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            
            //skriv gamle medlemmer til filen
            for (String s : nyListe)
            {
               printWriter.println(s);
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

	public static boolean tjekSortListe(String fornavn, String efternavn, String telefonnummer)
	{
      try
      {
         File file = new File(denSorteListe);
         
         if (!file.canRead())
         {
            System.out.println("Filen kunne ikke læses. Oplysningerne blev ikke gemt i systemet.");
            return false;
         }
         
         //læs hvert linje i filen
         Scanner scanner = new Scanner(file);
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
      
      }
      catch (IOException e)
      {
         System.out.println(e);
      }
      
      return false;
	}

   public static void laesRestanceListe()
   {
      try
      {
         File file = new File(medlemsFilnavn);
         
         if (!file.canRead())
         {
            System.out.println("Filen kunne ikke laeses. Vi kan ikke udprinte restanceListe.");
         }
         
         Scanner scanner = new Scanner(file);
         String[] linje;
         
         File restanceListe = new File("RestanceListe");
         PrintStream tilFil = new PrintStream(restanceListe);
         int medlemsnr = 0;
         String fornavn1 = "";
         String efternavn1 = "";
         
         while (scanner.hasNextLine())
         {
            linje = scanner.nextLine().split("\t");

            if (linje[13].equals("0"))
            {
                  medlemsnr = Integer.parseInt(linje[0]);
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