package com.whistleblower.app.rest;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.modelDto.AssignDto;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.StatusDto;
import com.whistleblower.app.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(ISSUE_URL_ROOT)
public class IssueController {

@Autowired
  private IssueService issueService;


@PostMapping(CREATE_NEW_ISSUE)
 ResponseEntity<?> createIssueAndUser(@Valid @RequestBody NewIssueDto newIssueDto,
                                                BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(newIssueDto);
    var newUser = issueService.createIssueAndUser(newIssueDto);
    return ResponseEntity.ok(newUser);
}

@GetMapping(GET_ALL_ISSUES)
ResponseEntity<List<Issue>> getNewIssues(){
    return ResponseEntity.ok(issueService.getAllNewIssues());
}



@PostMapping(ASSIGN_ISSUE)
ResponseEntity<?> assignIssue(@Valid @RequestBody AssignDto assignDto,
                              BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(assignDto);
    boolean assigned = issueService.assignIssue(assignDto);
    if(assigned){
        return ResponseEntity.ok(assignDto);
    }else {
        return ResponseEntity.badRequest().body(assignDto);
    }
}

    @PostMapping(CHANGE_ISSUE_STATUS)
    ResponseEntity<?> changeStatus(@Valid @RequestBody StatusDto statusDto,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(statusDto);

         boolean changed = issueService.changeIssueStatus(statusDto);

         if(changed){
             return ResponseEntity.ok(statusDto);
         }
         return  ResponseEntity.badRequest().body(statusDto);
    }


}
