import java.util.Scanner;
import java.time.LocalDate;

/**
* Klassen Medlemskab har ansvar for at haandtere funktioner knyttet til medlemmer,
* saasom at oprette medlem og opdatere medlemsoplysninger.
*
* @author Gruppe1
* @version 1.0
* @since 7.12.18
*/

public class Medlemskab
{
/**
* To variabler: medlem og nytMedlemsnummer
*/
   private Medlem medlem;
   private int nytMedlemsnummer = 0;

/**
* Konstruktoer uden parametre. I konstruktoeren faar variablen nytMedlemsnummer en vaerdi, som 
* modsvarer antallet af medlemmer, som vi faar ved at kalde paa metoden laesAntalMedlemmer()
* fra klassen Filhaandtering.
* Vi har i denne klasse ogsaa metoden hentNytMedlemsnummer(), som tilfoejer 1 til dette
* antal af medlemmer. Den kalder vi, naar vi opretter et nyt medlem, som dermed faar et nyt 
* medlemsnummer.
*/ 

   public Medlemskab()
   {
      this.nytMedlemsnummer = Filhaandtering.laesAntalMedlemmer();
   }
   
   //setter
   public void setNytMedlemsnummer(int nytMedlemsnummer)
   {
      this.nytMedlemsnummer = nytMedlemsnummer;
   }
   
   //getter
   public int getNytMedlemsnummer()
   {
      return this.nytMedlemsnummer = nytMedlemsnummer;
   }
   public Medlem getMedlem()
   {
      return this.medlem;
   }
   
   //toString
   public String toString()
   {
      return this.nytMedlemsnummer + ", " + this.getMedlem().toString();
   }
/**
* Metoden equals(): to medlemskaber er ens, hvis nytMedlemsnummer og medlem er ens
* @return boolean : true/false
*/
   public boolean equals (Medlemskab med)
   {
      if(this.nytMedlemsnummer == med.getNytMedlemsnummer()
      && this.medlem.equals(med.getMedlem()))
      {
         return true;
      }
      else 
      {
         return false;
      }
   }
   
/**
* Metoden opretMedlem(): opretter et nyt medlem ved at lave instans af Medlem og give objektet
* nye vaerdier ved hjaelp af set-metoder. Medlemsoplysninger faar vi via skanneren fra keyboard. 
* Til sidst bliver medlemmet gemt til filen ved at kalde paa metoden gemNytMedlem(medlem)
* fra klassen Filhaandtering.   
*/
   public void opretMedlem()
   {
      Scanner input = new Scanner(System.in);
      Medlem medlem = new Medlem();
      
      medlem.setMedlemsnummer(hentNytMedlemsnummer());
      System.out.println("Indtast fornavn");
      String fornavn = input.nextLine();
      medlem.setFornavn(fornavn);
      
      System.out.println("Indtast efternavn");
      String efternavn = input.nextLine();
      medlem.setEfternavn(efternavn);  
      
      System.out.println("Indtast cpr.nummer");
      String cpr = input.nextLine();
      medlem.setCprNummer(cpr); 
      
      System.out.println("Indtast 0 for hyggespiller eller 1 for turneringsspiller");
      while(!input.hasNextInt())//i tilfaelde af at brugeren ikke skriver et tal
         {
            String stringDummie = input.nextLine();
            System.out.println("Tast 0 eller 1");
         }
         int valg = input.nextInt();
         if(valg == 0)
         {
            medlem.setErTurneringsspiller(false); 
         }
         else if(valg == 1)
         {
            medlem.setErTurneringsspiller(true); 
         }
         else if (valg <0 || valg >1)
         {
            System.out.println("Vaelg 0 eller 1");
         }
         
       System.out.println("Indtast 1 for aktiv eller 0 for passiv");
       while(!input.hasNextInt())//i tilfaelde af at brugeren ikke skriver et tal
         {
            String stringDum = input.nextLine();
            System.out.println("Tast 0 eller 1");
         }
         int valgM = input.nextInt();
         if(valgM == 0)
         {
            medlem.setErAktiv(true); 
         }
         else if(valgM == 1)
         {
            medlem.setErAktiv(false); 
         }
         else if (valgM <0 || valg >1)
         {
            System.out.println("Vaelg 0 eller 1");
         }
         
         System.out.println("Indtast telefonnummer");
         String tlf = input.nextLine();
         medlem.getKontakt().setTelefonnummer(tlf);
         
         System.out.println("Indtast gadenavn");
         String gade = input.nextLine();
         medlem.getKontakt().setTelefonnummer(gade);     
         
         System.out.println("Indtast husnummer");
         String hus = input.nextLine();
         medlem.getKontakt().setTelefonnummer(hus);      
         
         System.out.println("Indtast postnummer");
         String post = input.nextLine();
         medlem.getKontakt().setTelefonnummer(post);     
         
         System.out.println("Indtast by");
         String by = input.nextLine();
         medlem.getKontakt().setTelefonnummer(by);     

         Filhaandtering.gemNytMedlem(medlem);   

   }
/**
* Metoden opdatereMedlem(): opdaterer medlemsoplysninger ved at brugeren vaelger i menuen 
* typen af oplysningen, der oenskes at opdatere. Den nye oplysning faar vi via skanneren fra 
* keyboard. Det opdaterede medlem gemmes ved at kalde paa metoden gemOpdateretMedlem() fra 
* klassen Filhaandtering.
* @param int medlemsnummer 
*/
   
   public void opdatereMedlem(int medlemsnummer)
   {
      Scanner console = new Scanner(System.in);
      boolean runProgram = true;
      
      while(runProgram)
      {
         visOpdateringsmenu();
         while(!console.hasNextInt()) //i tilfaelde af at brugeren ikke skriver et tal
         {
            System.out.println("Vaelg et tal mellem 0-4");
         }
         
         int valg = console.nextInt();
         
         if(valg < 0 || valg > 4) //i tilfaelde af at brugeren ikke vaelger et tal mellem 0-4
         {
            System.out.println("Vaelg et tal mellem 0-4");
         }
         
         if(valg == 1)
         {
            System.out.println("Skriv nyt fornavn");
            String fornavn = console.nextLine();
            Filhaandtering.gemOpdateretMedlem(medlemsnummer, 2, fornavn);
            runProgram = false;
         }
         
         if(valg == 2)
         {
            System.out.println("Skriv nyt efternavn");
            String efternavn = console.nextLine();
            Filhaandtering.gemOpdateretMedlem(medlemsnummer, 3, efternavn);
            runProgram = false;
         }
         
         if(valg == 3)
         {
            System.out.println("Skriv nyt telefonnummer");
            String nummer = console.nextLine();
            Filhaandtering.gemOpdateretMedlem(medlemsnummer, 11, nummer);
            runProgram = false;
         }
         
         if(valg==0)
         {
            System.out.println("Afslutter ...");
            runProgram = false;
         }
      }
   }
 
 /**
 * Metoden visOpdateringsmenu(): bruges til at vise opdateringsmenuen i forbindelse med at 
 * vi opretter et nyt medlem i metoden opdatereMedlem()
 */
   
   public void visOpdateringsmenu()
   {
      System.out.println("Hvad vil du opdatere?");
      System.out.println("1: Fornavn");
      System.out.println("2: Efternavn");
      System.out.println("3: Telefonnummer");
      System.out.println("0: Afslut");
   }
/**
* Metoden hentNytMedlemsnummer(): tilfoejer 1 til nytMedlemsnummer. I konstruktoeren fik 
* nytMedlemsnummer vaerdi, som modsvarer antallet af medlemmer paa medlemslisten. Det nye
* medlem faar dermed et medlemsnummer, som er antallet af medlemmer + 1. Vi kalder paa 
* metoden, naar vi opretter et nyt medlem.
* @return int : metoden returnerer et nyt medlemsnummer
*/  
   
   public int hentNytMedlemsnummer()
   {
      nytMedlemsnummer++;
      return nytMedlemsnummer;
   }

}