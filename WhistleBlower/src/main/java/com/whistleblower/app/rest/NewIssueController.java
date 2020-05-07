package com.whistleblower.app.rest;

import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.TempUserDto;
import com.whistleblower.app.service.NewIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class NewIssueController {

@Autowired
    NewIssueService newIssueService;


@PostMapping
 ResponseEntity<TempUserDto> newIssue(@Valid @RequestBody NewIssueDto newIssueDto,
                                      BindingResult bindingResult){
    if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new TempUserDto());

    return ResponseEntity.ok(newIssueService.createIssueAndUser(newIssueDto));
}

}
