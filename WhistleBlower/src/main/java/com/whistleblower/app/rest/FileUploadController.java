package com.whistleblower.app.rest;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.service.IssueService;
import com.whistleblower.app.storage.StorageFileNotFoundException;
import com.whistleblower.app.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.whistleblower.app.security.SecurityConstants.FILE_DOWNLOAD;

@RestController
public class FileUploadController {

    private final StorageService storageService;
    private final IssueService issueService;


    @Autowired
    public  FileUploadController(StorageService storageService, IssueService issueService){
        this.storageService = storageService;
        this.issueService = issueService;
    }



    @GetMapping(FILE_DOWNLOAD + "/{issueId}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable ("issueId") long issueId, Authentication authentication){

        Issue issue = issueService.getIssueByLawyerAndIssueId(authentication.getName(), issueId);
        if(issue != null){
            Resource file = storageService
                    .loadAsResource(issue.getId() + "/" + issue.getAttachment());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename()
                            + "\"").body(file);
        }else {
            return  ResponseEntity.badRequest().body("Bad request");
        }

    }



//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
//
//        Resource file = storageService
//.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename()
//        + "\"").body(file);
//    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file){
//
//        storageService.store(file,"test");
//
//        String filename = file.getOriginalFilename();
//        if(filename != null){
//            return ResponseEntity.ok( filename + " was successfully uploaded");
//        }else {
//            return ResponseEntity.badRequest().body("File not uploaded");
//        }
//    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?>
    handleStorageFileNotFound(StorageFileNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

}
