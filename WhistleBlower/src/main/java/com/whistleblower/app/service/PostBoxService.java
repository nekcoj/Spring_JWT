package com.whistleblower.app.service;

import com.whistleblower.app.entity.PostboxPost;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.repository.PostBoxRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;

@Service
public class PostBoxService {

    @Autowired
    private PostBoxRepository postBoxRepository;

    @Autowired
    private UserRepository userRepository;


    public List<PostboxPost> getUnRepliedMessages(String tokenId){
        var entityUser = userRepository.findByTokenId(tokenId);
        if(entityUser == null) return Collections.emptyList();
        else
      return postBoxRepository.findAllByTempUserIdAndRepliedFalseAndSentBy(entityUser.getId(),ROLE_LAWYER);
    }

    public boolean insertMessage(PostDto postDto) {
        var entityUser =  userRepository.findByTokenId(postDto.getTokenId());
        if(entityUser == null) return  false;

      //  var post = postBoxRepository.
        return true;
    }
}
