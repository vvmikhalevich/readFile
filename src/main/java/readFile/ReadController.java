package readFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class ReadController {

    @RequestMapping("/")
    public Map<String,Integer> readFileAndCountLetters(@RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {

        Map<String,Integer> CountOfWords = ReadFile.readFile();
        return CountOfWords;
    }
}
