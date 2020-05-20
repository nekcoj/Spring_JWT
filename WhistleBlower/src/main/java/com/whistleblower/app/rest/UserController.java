package com.whistleblower.app.rest;

import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.exceptionHandling.exeption.ResourceNotMappable;
import com.whistleblower.app.modelDto.GdprDto;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(USER_URL_ROOT)
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(GET_ALL_LAWYERS)
    ResponseEntity<List<UserEntity>> getAllLawyers(){
        return ResponseEntity.ok(userService.getAllLawyers());
    }

    @PostMapping(SET_GDPR_CONSENT)
    ResponseEntity<?> setConsent(@Valid @RequestBody GdprDto gdprDto, Authentication authentication,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));

        boolean consented = userService.updateConsent(gdprDto, authentication.getName());
        if(consented){
            return ResponseEntity.ok("GDPR godkännande uppdaterat!");
        } else return ResponseEntity.badRequest().body("Bad request");
    }
}
