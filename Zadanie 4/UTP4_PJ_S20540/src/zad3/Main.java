/**
 *
 *  @author Podleśny Jakub S20540
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    Function <String,List<String>> flines = (arg ->{
      try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(arg));
        String textLine;
        List<String> textLinesList = new ArrayList<>();
        while((textLine= bufferedReader.readLine()) != null){
          textLinesList.add(textLine);
        }
        return textLinesList;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    });
    Function <List<String>,String> join = (arg -> {
      String wholeString = "";
      for(int i = 0 ; i < arg.size(); i++){
        wholeString += arg.get(i);
      }
      return wholeString;
    });
    Function<String,List<Integer>> collectInts = (arg -> {
      Pattern pattern = Pattern.compile("-?\\d+");
      Matcher matcher = pattern.matcher(arg);
      List<Integer> listOFNumbers = new ArrayList<>();
      while(matcher.find()){
        listOFNumbers.add(Integer.parseInt(matcher.group()));
      }
      return listOFNumbers;
    });
    Function<List<Integer>,Integer> sum = (arg -> {
      int sumValue = 0;
      for (int i = 0; i < arg.size() ; i++) {
        sumValue += arg.get(i);
      }
      return sumValue;
    });
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
