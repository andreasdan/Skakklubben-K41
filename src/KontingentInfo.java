import java.util.*;
/** Denne her klasse har ansvar for kontingent info for de medlemmer der er hos Skakklubben K41.
   
   * @author Gruppe 1
   * @version 1
   * @since 07-12-2018
*/   

public class KontingentInfo
{
   /** Kontingent info har disse variabler :
       
       * int medlemsnummer
       * int kontingenttype
       * boolean harBetalt 
   */
   private int medlemsnummer;
   private String kontingenttype; 
   private boolean harBetalt; 
   
   /** Det er default konstruktoren 
       
      * public kontingentInfo()
      
   */      
   public KontingentInfo()
   {
      this.medlemsnummer = 0;
      this.kontingenttype = "";
      this.harBetalt = false;
   
   }
   /** Det er klassens konstruktor
   
      * public KontingentInfo(int medlemsnummer, int kontingenttype, boolean harBetalt)
   */   
   public KontingentInfo(int medlemsnummer, String kontingenttype, boolean harBetalt)
   {
      this.medlemsnummer = medlemsnummer;
      this.kontingenttype = kontingenttype;
      this.harBetalt = harBetalt; 
   
   }
   
   /** Det er klassens setters og getters
   */
   
   public void setMedlemsnummer(int medlemsnummer)
   {
      this.medlemsnummer = medlemsnummer; 
   }
   
   public int getMedlemsnummer()
   {
   
      return this.medlemsnummer;
   }
   
   public void setKontingenttype(String konntingenttype)
   {
       this.kontingenttype = kontingenttype;
   }
   
   public String getKontingenttype()
   {
        return this.kontingenttype;
   }
   
   public void setHarBetalt(boolean harBetalt)
   {
      this.harBetalt = harBetalt;
   }
   
   public boolean getHarBetalt()
   {
   
      return this.harBetalt;
   }

}