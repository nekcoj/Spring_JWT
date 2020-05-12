package com.whistleblower.app.rest;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.modelDto.MoveDto;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.service.NewIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(NEW_ISSUE_URL_ROOT)
public class IssueController {

@Autowired
    NewIssueService newIssueService;


@PostMapping(SEND_NEW_ISSUE_URL)
 ResponseEntity<?> createIssueAndUser(@Valid @RequestBody NewIssueDto newIssueDto,
                                                BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(newIssueDto);
    var newUser = newIssueService.createIssueAndUser(newIssueDto);
    return ResponseEntity.ok(newUser);
}

@GetMapping(GET_NEW_ISSUE_URL)
ResponseEntity<List<Issue>> getNewIssues(){
    return ResponseEntity.ok(newIssueService.getAll());
}

@PostMapping
ResponseEntity<?> assignIssue(@Valid @RequestBody MoveDto moveDto,
                              BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(moveDto);
    boolean moved  = newIssueService.assignIssue(moveDto);
    if(moved){
        return ResponseEntity.ok(moveDto);
    }else {
        return ResponseEntity.badRequest().body(moveDto);
    }

}


}
