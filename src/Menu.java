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

    Scanner console = new Scanner(System.in);

    /**
     * Konstruktor til menu.
     */

    public Menu()
    {

    }


    /**
     * Metoden korerProgram er en metode til at koere menuen.
     */

    public void korerProgram()
    {
        boolean runProgram = true;

        while (runProgram)
        {
            visHovedmenu();
            while (!console.hasNextInt()) //i tilfaelde af at brugeren ikke skriver et tal
            {
                System.out.println("Vaelg et tal mellem 0-2");
            }

            int valg = console.nextInt();

            if (valg < 0 || valg > 2) //i tilfaelde af at brugeren ikke vaelger et tal mellem 0-2
            {
                System.out.println("Vaelg et tal mellem 0-2");
            }


            if (valg == 0)
            {
                System.out.println("Afslutter ...");
                runProgram = false;
            }

            if (valg == 1)
            {
                Medlemskab medlemskab = new Medlemskab();

                visMedlemskabsMenu();
                while (!console.hasNextInt()) //i tilfaelde af at brugeren ikke skriver et tal
                {
                    System.out.println("Vaelg et tal mellem 0-2");
                }

                int valg1 = console.nextInt();

                if (valg1 < 0 || valg1 > 2) //i tilfaelde af at brugeren ikke vaelger et tal mellem 0-2
                {
                    System.out.println("Vaelg et tal mellem 0-2");
                }

                if (valg1 == 0)
                {
                    System.out.println("Afslutter ...");
                    runProgram = false;
                }

                if (valg1 == 1)
                {
                    System.out.println("Du har valgt at oprette et nyt medlem.");
                    medlemskab.opretMedlem();
                }

                if (valg1 == 2)
                {
                    System.out.println("Du har valgt at opdatere et medlems stamoplysninger.");
                    System.out.println("Skriv medlemsnummer:");

                    while (!console.hasNextInt())
                    {
                        System.out.println("Skriv et tal");
                    }

                    int nummer = console.nextInt();
                    String dummy = console.nextLine();
                    medlemskab.opdatereMedlem(nummer);

                }

            }

            if (valg == 2)
            {

                visKontingenthaandteringMenu();
                while (!console.hasNextInt()) //i tilfaelde af at brugeren ikke skriver et tal
                {
                    System.out.println("Vaelg et tal mellem 0-2");
                }

                int valg2 = console.nextInt();

                if (valg2 < 0 || valg2 > 2) //i tilfaelde af at brugeren ikke vaelger et tal mellem 0-2
                {
                    System.out.println("Vaelg et tal mellem 0-2");
                }

                if (valg2 == 0)
                {
                    System.out.println("Afslutter ...");
                    runProgram = false;
                }

                if (valg2 == 1)
                {
                    String[] priser = Filhaandtering.laesPriser();
                    for (String pris : priser)
                    {
                        System.out.println(pris);
                    }
                }

                if (valg2 == 2)
                {
                    Filhaandtering.laesRestanceListe();
                }


            }

        }


    }

    /**
     * visHovedmenu printer teksten til hovedmenuen.
     */

    public void visHovedmenu()
    {
        System.out.println("Velkommen!");
        System.out.println("Hvad oensker du at foretage dig?");
        System.out.println("1: Medlemskab");
        System.out.println("2: Kontingent");
        System.out.println("0: Afslut");

    }

    /**
     * visMedlemskabsmenu printer teksten til medlemskabsmenuen.
     */

    public void visMedlemskabsMenu()
    {
        System.out.println("Du har valgt medlemskab.");
        System.out.println("Hvad Oensker du at foretage dig?");
        System.out.println("1: Oprette et nyt medlem");
        System.out.println("2: Opdatere stamoplysninger paa et eksisterende medlem");
        System.out.println("0: Afslut");

    }

    /**
     * visKontingenthaandteringMenu printer teksten til kontingenthaandteringsmenuen.
     */

    public void visKontingenthaandteringMenu()
    {
        System.out.println("Du har valgt kontingent.");
        System.out.println("Hvad oensker du at foretage dig?");
        System.out.println("1: Se priser");
        System.out.println("2: Se medlemmer i restance");
        System.out.println("0: Afslut");

    }
}