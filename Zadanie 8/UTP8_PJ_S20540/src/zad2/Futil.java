package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {

        try {
            if (Files.exists(Paths.get(resultFileName))) {
                Files.delete(Paths.get(resultFileName));
            }
            if (!Files.exists(Paths.get(resultFileName))) {
                Files.createFile(Paths.get(resultFileName));
            }
            Stream<Path> paths = Files.
                    walk(Paths.get(dirName));
            paths.filter(line -> {
                if(!Files.isDirectory(line)){
                    return true;
                }else{
                    return false;
                }
            }).collect(Collectors.toList()).forEach(line -> {
                try {
                    List<String> allLinesFromOneFile = Files.readAllLines(line, Charset.forName("Cp1250"));
                    Files.write(Paths.get(resultFileName), allLinesFromOneFile,StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
