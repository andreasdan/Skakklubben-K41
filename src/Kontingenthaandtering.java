import java.util.*;

/** 
* Kontingenthaandtering administrerer kontingentinfomationer.
* Klassen kan skabe lister vedroerende medlemmernes kontingenter.
* Det drejer sig indtil videre om info om restance og priser
*
* @author   Gruppe1
* @version  1.0
* @since    07-12-2018
*/

public class Kontingenthaandtering
{

   private ArrayList<KontingentInfo> kontingentinfo;
   
/**
* En tom konstruktor.
*/
   
   public Kontingenthaandtering()
   {
   
   }
   
/**
* Metoden hentKontingentliste henter en ArrayList af kontingentinfo
*
* @return   ArrayList<KontingentInfo>  Returnerer en liste af KontingentInfo
*/
   
   public ArrayList<KontingentInfo> hentKontingentliste()
   {
   
      return kontingentinfo;
   }
   
   public void visRestanceliste()
   {
   
   }
   
/**
**/
   
   public void hentPriser()
   {
      
      String[] priser = Filhaandtering.laesPriser();
      for (String pris : priser)
      {
         System.out.println(pris);
      }  
   }

}