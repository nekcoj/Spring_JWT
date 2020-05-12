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

@RestController
@RequestMapping("/post")
public class PostBoxController {

    @Autowired
    private PostBoxService postBoxService;


    @PostMapping("new-messages-user")
    ResponseEntity<?> getMessages(@Valid @RequestBody TokenId tokenId,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error While Mapping Object"));
        return ResponseEntity.ok(postBoxService.getUnRepliedMessages(tokenId.getTokenId()));
    }

    @PostMapping("send-message-user")
    ResponseEntity<?> insertMessage(@Valid @RequestBody PostDto postDto,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error While Mapping Object"));
        postBoxService.insertReplyFromTempUser(postDto);

        return ResponseEntity.ok("Message received!");
    }


}
