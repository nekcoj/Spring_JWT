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

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(POSTBOX_URL_ROOT)
public class PostBoxController {

    @Autowired
    private PostBoxService postBoxService;


    @PostMapping(POSTBOX_SEND_BY_LAWYER)
    ResponseEntity<?> sendMessageByLawyer(@Valid @RequestBody PostDto postDto, Authentication authentication,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));
         boolean sent =   postBoxService.sendMessageByLawyer(postDto , authentication.getName());

         if(sent) return ResponseEntity.ok("Meddelande skickat!");
         else  return ResponseEntity.badRequest().body("Bad request");
    }




    @PostMapping(POSTBOX_REPLY_BY_USER)
    ResponseEntity<?> replyMessageByLawyer(@Valid @RequestBody PostDto postDto, Authentication authentication,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));

        boolean replied = postBoxService.replyByTempUser(postDto, authentication.getName());

        if(replied) return ResponseEntity.ok("Meddelande skickat!");
        else return ResponseEntity.badRequest().body("Bad request");
    }



    @GetMapping(POSTBOX_GET_ALL_FOR_USER)
    ResponseEntity<?> getMessagesForTempUser(Authentication authentication){
        var inboxMessages = postBoxService.getMessagesForTempUser(authentication.getName());
        return ResponseEntity.ok(inboxMessages);
    }


    @GetMapping(POSTBOX_GET_ALL_FOR_LAWYER)
    ResponseEntity<?> getMessagesForLawyer(Authentication authentication){
        var inboxMessages = postBoxService.getMessagesForLawyer(authentication.getName());
        return ResponseEntity.ok(inboxMessages);
    }


}
