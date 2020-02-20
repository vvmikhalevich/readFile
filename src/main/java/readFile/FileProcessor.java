package readFile;

import java.io.IOException;
import java.util.Map;

public interface FileProcessor {

    String name = "c:/SampleText.txt";;

    Map<String, Integer> countWordsInFile(String name) throws IOException;
}
