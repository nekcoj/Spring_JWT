package com.whistleblower.app.rest;

import com.whistleblower.app.exceptionHandling.exeption.ResourceNotMappable;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.modelDto.TokenId;
import com.whistleblower.app.service.PostBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(POSTBOX_URL_ROOT)
public class PostBoxController {

    @Autowired
    private PostBoxService postBoxService;


    @PostMapping(POSTBOX_SEND_BY_LAWYER)
    ResponseEntity<?> sendMessageByLawyer(@Valid @RequestBody PostDto postDto,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));
         boolean sent =   postBoxService.sendMessageByLawyer(postDto);

         if(sent) return ResponseEntity.ok("Message sent");
         else  return ResponseEntity.badRequest().body("Bad request");
    }




    @PostMapping(POSTBOX_REPLY_USER)
    ResponseEntity<?> replyMessageByLawyer(@Valid @RequestBody PostDto postDto,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));

        boolean replied = postBoxService.replyByTempUser(postDto);

        if(replied) return ResponseEntity.ok("Message received!");
        else return ResponseEntity.badRequest().body("Bad request");
    }



    @PostMapping(POSTBOX_GET_ALL_FOR_USER)
    ResponseEntity<?> getMessagesForTempUser(@Valid @RequestBody TokenId tokenId,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));

        var inboxMessages = postBoxService.getMessagesForTempUser(tokenId);
        return ResponseEntity.ok(inboxMessages);
    }


    @PostMapping(POSTBOX_GET_ALL_FOR_LAWYER)
    ResponseEntity<?> getMessagesForLawyer(@Valid @RequestBody TokenId tokenId,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error"));

        var inboxMessages = postBoxService.getMessagesForLawyer(tokenId);
        return ResponseEntity.ok(inboxMessages);
    }


}
