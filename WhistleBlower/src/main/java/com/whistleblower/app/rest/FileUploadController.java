package com.whistleblower.app.rest;

import com.whistleblower.app.storage.StorageFileNotFoundException;
import com.whistleblower.app.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public  FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){

        Resource file = storageService
.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename()
        + "\"").body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file){

        storageService.store(file,"test");

        String filename = file.getOriginalFilename();
        if(filename != null){
            return ResponseEntity.ok( filename + " was successfully uploaded");
        }else {
            return ResponseEntity.badRequest().body("File not uploaded");
        }


    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?>
    handleStorageFileNotFound(StorageFileNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

}
