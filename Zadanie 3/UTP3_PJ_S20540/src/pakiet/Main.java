/**
 *
 *  @author Podleśny Jakub S20540
 *
 */

package pakiet;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(  dataArg -> {
                                 String [] dataRowTMPSel = dataArg.split(" ");
                                 if(dataRowTMPSel[0].equals("WAW"))
                                   return true;
                                 else
                                   return false;
                               }
                        )
                       .mapEvery( dataArg -> {
                                 String [] dataRowTMPCre = dataArg.split(" ");
                                 String newData = "to " + dataRowTMPCre[1] + " - price in PLN: " + Math.round(Integer.parseInt(dataRowTMPCre[2]) * xrate);
                                 return newData;
                               }
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
