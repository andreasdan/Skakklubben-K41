import java.util.*;
/** Denne her klasse har til ansvar at have styr paa kontaktoplysninger 
  * Komposition: medlemmer har kontaktoplysninger (kontaktoplysninger ville ikke eksistere hvis man sletter medlem.
  
   * @author Gruppe 1
   * @version 1
   * @since 07-12-2018
*/    
public class Kontaktoplysninger
{
    /** Kontaktoplysninger har disse variabler :
       
       * String telefonnummer
       * String gadenavn
       * String husnummer
       * String postnummer
       * String by 
   */
   String telefonnummer;
   String gadenavn;
   String husnummer;
   String postnummer;
   String by;
   String email;

    /** Det er default konstruktoren 
       
      * public Kontaktoplysninger()
      
   */
   public Kontaktoplysninger()
   {
      /*this.telefonnummer = "";
      this.gadenavn = "";
      this.husnummer = "";
      this.postnummer = "";
      this.by = "";
      this.email = "";*/
   }
   
   /** Det er klassens konstruktor
   
      * public Kontaktoplysninger(String telefonnummer, String gadenavn, String husnummer, String postnummer, String by)
   */ 
   public Kontaktoplysninger(String telefonnummer, String gadenavn, String husnummer, String postnummer, String by, String email)
   {
      this.telefonnummer = telefonnummer;
      this.gadenavn = gadenavn;
      this.husnummer = husnummer;
      this.postnummer = postnummer;
      this.by = by;
      this.email = email;
   
   }
   
    /** Det er klassens setters og getters
   */
   public void setEmail(String email)
   {
      this.email = email;
   }
   
   public String getEmail()
   {
      return this.email;
   }
   
   public void setTelefonnummer(String telefonnummer)
   {
      this.telefonnummer = telefonnummer;
   }
   
   public String getTelefonnummer()
   {
   
      return this.telefonnummer; 
   }
   
   public void setGadenavn(String gadenavn)
   {
      this.gadenavn = gadenavn;
   }
   
   public String getGadenavn()
   {
      return this.gadenavn;
   } 
   
   public void setHusnummer(String husnummer)
   {
      this.husnummer = husnummer;
   }
   
   public String getHusnummer()
   {
      return this.husnummer;
   }
   
   public void setPostnummer(String postnummer)
   {
      this.postnummer = postnummer;
   }
   
   public String getPostnummer()
   {
      return this.postnummer;
   }
   
   public void setBy(String by)
   {
      this.by = by;
   }
   
   public String getBy()
   {
      return this.by;
   }
   
   /** her har vi en metode der hedder toString.
     * @return en String resultat af det medlem (som er resultatet af de get metoder den har kaldt og viser det på consolen)
   */  
        
   public String toString()
   {
      String resultat = getTelefonnummer() + "\n\t " + getGadenavn() + "\n\t " + getHusnummer() + "\n\t " + getPostnummer() + "\n\t " + getBy();
      
      return resultat;     
   
   }
   
     /** her har vi en metode der hedder tilFil. 
     * @return en String resultat af et medlem (som er resultatet af de get metoder den har kaldt og viser det i vores fil)
   */ 
   public String tilFil()
   {
      String resultat = getTelefonnummer() + "\t " + getGadenavn() + "\t " + getHusnummer() + "\t " + getPostnummer() + "\t " + getBy();
      
      return resultat;     
   
   }

}