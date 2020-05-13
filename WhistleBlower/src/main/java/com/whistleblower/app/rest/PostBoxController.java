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

import static com.whistleblower.app.security.SecurityConstants.POSTBOX_URL_ROOT;

@RestController
@RequestMapping(POSTBOX_URL_ROOT)
public class PostBoxController {

    @Autowired
    private PostBoxService postBoxService;


    @PostMapping("new-messages-user")
    ResponseEntity<?> getMessages(@Valid @RequestBody TokenId tokenId,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error While Mapping Object"));

        return ResponseEntity.ok("");
    }

    @PostMapping("send-message-user")
    ResponseEntity<?> insertMessage(@Valid @RequestBody PostDto postDto,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()) return ResponseEntity.unprocessableEntity().body(new ResourceNotMappable("Error While Mapping Object"));


        return ResponseEntity.ok("Message received!");
    }


}
