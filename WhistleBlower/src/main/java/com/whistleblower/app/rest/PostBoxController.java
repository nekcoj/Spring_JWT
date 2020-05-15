package com.whistleblower.app.rest;

import com.whistleblower.app.exceptionHandling.exeption.ResourceNotMappable;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.service.PostBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostBoxController {

    @Autowired
    private PostBoxService postBoxService;


    @GetMapping("new-messages-user")
    ResponseEntity<?> getMessages(Authentication authentication){
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(postBoxService.getUnRepliedMessages(authentication.getName()));
        }else {
            return ResponseEntity.badRequest().body("Bad request");
        }

    }

    @PostMapping("send-message-user")
    ResponseEntity<?> insertMessage(@Valid @RequestBody PostDto postDto,
                                    BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error While Mapping Object"));
        postBoxService.insertReplyFromTempUser(postDto, authentication);

        return ResponseEntity.ok("Message received!");
    }


}
