package com.whistleblower.app.rest;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.modelDto.AssignDto;
import com.whistleblower.app.modelDto.IssueDto;
import com.whistleblower.app.modelDto.StatusDto;
import com.whistleblower.app.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.Date;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(ISSUE_URL_ROOT)
public class IssueController {

@Autowired
  private IssueService issueService;




    @PostMapping(CREATE_NEW_ISSUE)
    ResponseEntity<?> createIssueAndUser(@RequestParam("categoryId") long categoryId ,
                                         @RequestParam("whenIssue") String whenIssue,
                                         @RequestParam("whereIssue") String whereIssue,
                                         @RequestParam("details") String details,
                                         @RequestParam("employeeAwareness") String employeeAwareness,
                                         @RequestParam("attachment") MultipartFile attachment){
        IssueDto issue = new IssueDto();
        issue.setCategoryId(categoryId);
        issue.setWhenIssue(whenIssue);
        issue.setWhereIssue(whereIssue);
        issue.setDetails(details);
        issue.setEmployeeAwareness(employeeAwareness);
        issue.setAttachmentFileName(attachment.getOriginalFilename());

        var newUser = issueService.createIssueAndUser(issue,attachment);


        return ResponseEntity.ok(newUser);
    }

//@PostMapping(CREATE_NEW_ISSUE)
// ResponseEntity<?> createIssueAndUser(@Valid @RequestBody IssueDto issueDto,
//                                                BindingResult bindingResult){
//    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(issueDto);
//    var newUser = issueService.createIssueAndUser(issueDto);
//    return ResponseEntity.ok(newUser);
//}

@GetMapping(GET_ALL_ISSUES_FOR_ADMIN)
ResponseEntity<?> getAllIssuesAdmin(){
    return ResponseEntity.ok(issueService.getAllIssuesForAdmin());
}



@PostMapping(ASSIGN_ISSUE)
ResponseEntity<?> assignIssue(@Valid @RequestBody AssignDto assignDto,
                              BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(assignDto);
    boolean assigned = issueService.assignIssue(assignDto);
    if(assigned){
        return ResponseEntity.ok(assignDto);
    }else {
        return ResponseEntity.badRequest().body("Bad request: Not assigned!");
    }
}

    @PostMapping(CHANGE_ISSUE_STATUS)
    ResponseEntity<?> changeStatus(@Valid @RequestBody StatusDto statusDto, Authentication authentication,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(statusDto);

         boolean changed = issueService.changeIssueStatus(statusDto, authentication);

         if(changed){
             return ResponseEntity.ok(statusDto);
         }
         return  ResponseEntity.badRequest().body(statusDto);
    }

    @GetMapping(GET_ALL_ISSUES_FOR_LAWYER)
    ResponseEntity<?> getIssues(Authentication authentication){
          var issues = issueService.getIssuesForLawyer(authentication.getName());
          return ResponseEntity.ok(issues);
    }

    @GetMapping(GET_ISSUE_STATUS_USER)
    ResponseEntity<?> getIssueStatusForUser(Authentication authentication){
        var issues = issueService.getIssueStatusForUser(authentication.getName());
        if(issues != null) {
            return ResponseEntity.ok(issues);
        } else {
            return ResponseEntity.badRequest().body("No issue found!");
        }
    }

    @PostMapping(INACTIVATE_ISSUE + "/{issueId}/{value}")
    ResponseEntity<?> activateInactivateIssue(@PathVariable long issueId, @PathVariable boolean value){
        Issue issue = issueService.activateInactivateIssue(issueId, value);
        if(issue != null){
            return ResponseEntity.ok().body("Changed!");
        }else {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @PostMapping(CHANGE_CATEGORY + "/{issueId}/{categoryId}")
    ResponseEntity<?> changeCategoryForIssue(@PathVariable long issueId, @PathVariable long categoryId){
        Issue issue = issueService.changeIssueCategory(issueId, categoryId);
        if(issue != null){
            return ResponseEntity.ok().body("Category changed!");
        } else {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }


}
