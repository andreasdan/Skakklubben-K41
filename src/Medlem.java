import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Medlem indeholder oplysninger paa medlemmerne i Skakklubben K41
 *
 * @author Gruppe1
 * @version 1.0
 * @since 07-12-2018
 */


public class Medlem
{

    /**
     * Variabler som hvert medlem skal have
     */

    private int medlemsnummer;
    private String fornavn;
    private String efternavn;
    private LocalDate oprettelsesdato;
    private LocalDate foedselsdato;
    private String cprNummer;
    private boolean erTurneringsspiller;
    private boolean erAktiv;
    private Kontaktoplysninger kontakt;

    /**
     * Konstruktoren indeholder en metode der opretter en instans af Kontaktoplysninger (composition)
     */

    public Medlem()
    {
        this.kontakt = new Kontaktoplysninger();
    }

    public void setMedlemsnummer(int medlemsnummer)
    {
        this.medlemsnummer = medlemsnummer;
    }

    public int getMedlemsnummer()
    {
        return this.medlemsnummer;
    }

    public void setFornavn(String fornavn)
    {
        this.fornavn = fornavn;
    }

    public String getFornavn()
    {
        return this.fornavn;
    }

    public void setEfternavn(String efternavn)
    {
        this.efternavn = efternavn;
    }

    public String getEfternavn()
    {
        return this.efternavn;
    }

    public void setOprettelsesdato(LocalDate oprettelsesdato)
    {
        this.oprettelsesdato = oprettelsesdato;
    }

    public void setFoedselsdato(LocalDate foedselsdato)
    {
        this.foedselsdato = foedselsdato;
    }

    public LocalDate getOprettelsesdato()
    {
        return this.oprettelsesdato;
    }

    public LocalDate getFoedselsdato()
    {
        return this.foedselsdato;
    }

    public void setCprNummer(String cprNummer)
    {
        this.cprNummer = cprNummer;
    }

    public String getCprNummer()
    {
        return cprNummer;
    }

    public void setErTurneringsspiller(boolean erTurneringsspiller)
    {
        this.erTurneringsspiller = erTurneringsspiller;
    }

    public boolean getErTurneringsspiller()
    {
        return this.erTurneringsspiller;
    }

    public void setErAktiv(boolean erAktiv)
    {
        this.erAktiv = erAktiv;
    }

    public boolean getErAktiv()
    {
        return this.erAktiv;
    }

    public Kontaktoplysninger getKontakt()
    {
        return this.kontakt;
    }

    public String toString()
    {
        return "Navn: " + getFornavn() + " " + getEfternavn();
    }

    //True = 1 og False = 0, konvertere boolean om til int til filskrivning
    private int konverterBoolean(boolean bool)
    {
        if (bool)
        {
            return 1;
        } else
        {
            return 0;
        }
    }

    /**
     * Metoden equal sammenligner to objekter.
     * Hvis to medlemsobjekter har samme fornavn, efternavn og telefonnummer, anses de for ens.
     *
     * @return boolean Returnerer true hvis medlemmerne er ens.
     */

    public boolean equals(Object o)
    {
        if (o instanceof Medlem)
        {
            Medlem other = (Medlem) o;
            return getFornavn().equals(other.getFornavn()) && getEfternavn().equals(other.getEfternavn()) && getKontakt().getTelefonnummer().equals(other.getKontakt().getTelefonnummer());
        } else
        {
            return false;
        }
    }

    /**
     * tilFil genererer en string med oplysninger paa et medlem.
     *
     * @return String  returnerer oplysninger paa et medlem. foedselsdato = LocalDate.parse("1998-14-03")
     */

    public String tilFil()
    {
        int kontingenttype;

        long alderIAAr = ChronoUnit.YEARS.between(foedselsdato, LocalDate.now());
        if (alderIAAr < 18)
        {
            kontingenttype = 0; //junior
        } else
        {
            kontingenttype = 1; //senior
        }

        int turneringsspiller = konverterBoolean(erTurneringsspiller);
        int spillerErAktiv = konverterBoolean(erAktiv);

        String resultat =
                medlemsnummer + "\t"
                        + oprettelsesdato.toString() + "\t"
                        + fornavn + "\t"
                        + efternavn + "\t"
                        + kontakt.getGadenavn() + "\t"
                        + kontakt.getPostnummer() + "\t"
                        + kontakt.getHusnummer() + "\t"
                        + kontakt.getBy() + "\t"
                        + foedselsdato.toString() + "\t"
                        + kontingenttype + "\t"
                        + kontakt.getEmail() + "\t"
                        + kontakt.getTelefonnummer() + "\t"
                        + turneringsspiller + "\t"
                        + "0" + "\t" //harBetalt er altid 0 fordi der først skal faktureres osv.
                        + spillerErAktiv;

        return resultat;
    }

}