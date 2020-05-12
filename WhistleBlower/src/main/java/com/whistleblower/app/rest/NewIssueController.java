package com.whistleblower.app.rest;

import com.whistleblower.app.entity.NewIssue;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.TempUserDto;
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
public class NewIssueController {

@Autowired
    NewIssueService newIssueService;


@PostMapping(SEND_NEW_ISSUE_URL)
 ResponseEntity<TempUserDto> newIssue(@Valid @RequestBody NewIssueDto newIssueDto,
                                      BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new TempUserDto());

    return ResponseEntity.ok(newIssueService.createIssueAndUser(newIssueDto));
}

@GetMapping(GET_NEW_ISSUE_URL)
ResponseEntity<List<NewIssue>> getNewIssues(){
    return ResponseEntity.ok(newIssueService.getAll());
}




}
