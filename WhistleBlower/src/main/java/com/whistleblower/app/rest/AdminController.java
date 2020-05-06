package com.whistleblower.app.rest;

import com.whistleblower.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdminController  {

@Autowired
    AdminService adminService;


}
