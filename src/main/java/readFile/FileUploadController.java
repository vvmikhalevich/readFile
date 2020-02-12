package readFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

    public static final String FILE_FOLDER = "d:\\";

    @RequestMapping(method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") final MultipartFile file,
            final RedirectAttributes redirectAttributes)
                    throws IOException, GeneralSecurityException {
        String originalFilename = file.getOriginalFilename(); // to DB
        String contentType = file.getContentType();// to DB
        String uuid = UUID.randomUUID().toString(); // to DB

        System.out.printf("Uploaded file %s", originalFilename);

        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/";
    }

    @RequestMapping(value = "/image")
    public @ResponseBody byte[] getImage(
            @RequestParam(name = "uuid", required = true) final String uuid)
                    throws IOException {

        try (InputStream initialStream = new FileInputStream(FILE_FOLDER + uuid);) {
            byte[] byteArray = IOUtils.toByteArray(initialStream);
            return byteArray;
        }
    }
}
