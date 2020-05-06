package com.whistleblower.app.rest;

import com.whistleblower.app.service.NewIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NewIssueController {

@Autowired
    NewIssueService newIssueService;


}
