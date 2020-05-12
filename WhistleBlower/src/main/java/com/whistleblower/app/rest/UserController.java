package com.whistleblower.app.rest;

import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.GET_ALL_LAWYERS;
import static com.whistleblower.app.security.SecurityConstants.USER_URL_ROOT;

@RestController
@RequestMapping(USER_URL_ROOT)
public class UserController {

@Autowired
UserService userService;


@GetMapping(GET_ALL_LAWYERS)
ResponseEntity<List<UserEntity>> getAllLawyers(){
    return ResponseEntity.ok(userService.getAllLawyers());
}

}
