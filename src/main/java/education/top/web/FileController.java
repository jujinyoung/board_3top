package education.top.web;

import education.top.domain.FileStore;
import education.top.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String uploadForm() {
        return "file/form";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()){
            redirectAttributes.addAttribute("success", "fail");
            return "redirect:/file/upload";
        }

        fileService.save(file);
        redirectAttributes.addAttribute("success", "success");
        return "redirect:/file/upload";
    }

    @GetMapping("/view")
    public String viewFile(Model model){
        model.addAttribute("fileStores", fileService.findAll());
        return "/file/view";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {
        FileStore fileStore = fileService.findById(id);
        String storeFileName = fileStore.getStoreFileName();
        String uploadFileName = fileStore.getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileService.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
