package com.whistleblower.app.rest;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.service.IssueService;
import com.whistleblower.app.storage.StorageFileNotFoundException;
import com.whistleblower.app.storage.StorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    public void serveFile(@PathVariable ("issueId") long issueId, Authentication authentication,
                          HttpServletResponse response) {

        Issue issue = issueService.getIssueByLawyerAndIssueId(authentication.getName(), issueId);
        if (issue != null) {
            Resource file = storageService
                    .loadAsResource(issue.getId() + "/" + issue.getAttachment());
            response.setContentType("application/json");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename()
                            + "\"");

            OutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream in = null;
            try {
                in = new FileInputStream(file.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // copy from in to out
            try {
                assert in != null;
                IOUtils.copy(in,out);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.getFile().delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
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



}
