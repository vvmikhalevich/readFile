package readFile;

import java.io.IOException;
import java.util.Map;

public interface FileProcessor {



    Map<String, Integer> countWordsInFile(String name) throws IOException;
}
