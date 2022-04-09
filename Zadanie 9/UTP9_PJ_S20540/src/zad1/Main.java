/**
 * @author Podleśny Jakub S20540
 */
package zad1;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        try {
            Map<String, List<String>> anagrams = Files.lines(Paths.get("unixdict.txt")).collect(Collectors.groupingBy(oneLine -> {
                char[] tmp = oneLine.toCharArray();
                Arrays.sort(tmp);
                return new String(tmp);
            }));
            Optional<Map.Entry<String, List<String>>> map = anagrams.entrySet().stream().max(Comparator.comparingInt(stringListEntry -> stringListEntry.getValue().size()));
            Map<String, List<String>> fAnagramsMap = anagrams.entrySet().stream().filter(listSize -> map.get().getValue().size() == listSize.getValue().size())
                    .map(list->{
                        list.getValue().sort(Comparator.naturalOrder()); return list;
                    }).sorted(Comparator.comparing(stringListEntry -> stringListEntry.getValue().get(0))).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a,b)->b,LinkedHashMap::new));
            fAnagramsMap.forEach((e1,e2)->{
                StringBuilder fS = new StringBuilder();
                e2.forEach(s->fS.append(" ").append(s)); System.out.println(fS);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
