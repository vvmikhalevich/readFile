package readFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ReadController {
    private final FileProcessor processor;

    public ReadController(FileProcessor processor) {
        this.processor = processor;
    }

    @RequestMapping("/")
    public Map<String,Integer> readFileAndCountLetters(String name) throws IOException {
        name = "c:/SampleText.txt";
        Map<String, Integer> wordCountDictionary = processor.countWordsInFile(name);
        return wordCountDictionary;
    }
}
