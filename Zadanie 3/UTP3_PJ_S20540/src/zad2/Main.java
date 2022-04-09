/**
 *
 *  @author Podleśny Jakub S20540
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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
    List<String> result =
            dest.stream().filter(arg -> {
              String[] listCities = arg.split(" ");
              if (listCities[0].equals("WAW"))
                return true;
              else
                return false;
            }).map(arg -> {
              String[] listCities = arg.split(" ");
              String newData = "to " + listCities[1] + " - price in PLN: " + Math.round(Integer.parseInt(listCities[2])* ratePLNvsEUR);
              return newData;
            }).collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
