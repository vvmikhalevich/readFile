package readFile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ReadController {

    @RequestMapping("/")
    public Map<String,Integer> readFileAndCountLetters(@RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {

        return ReadFile.readFile();
    }
}
