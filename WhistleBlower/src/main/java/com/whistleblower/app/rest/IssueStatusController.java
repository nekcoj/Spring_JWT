package com.whistleblower.app.rest;

import com.whistleblower.app.service.IssueStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(ISSUE_STATUS_URL_ROOT)
public class IssueStatusController {

@Autowired
  private IssueStatusService issueStatusService;



@GetMapping(GET_ALL_ISSUE_STATUS)
ResponseEntity<?> getAllIssueStatus(){
    return ResponseEntity.ok(issueStatusService.getAll());
}



}
