package zad1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {

        try {
            if (Files.exists(Paths.get(resultFileName))) {
                Files.delete(Paths.get(resultFileName));
            }
            if (!Files.exists(Paths.get(resultFileName))) {
                Files.createFile(Paths.get(resultFileName));
            }
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

                    List<String> allLinesFromOneFile = Files.readAllLines(path, Charset.forName("Cp1250"));
                    Files.write(Paths.get(resultFileName), allLinesFromOneFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
