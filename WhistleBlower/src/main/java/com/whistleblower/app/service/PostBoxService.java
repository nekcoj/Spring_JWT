package com.whistleblower.app.service;

import com.whistleblower.app.entity.PostboxPost;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.repository.PostBoxRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;
import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;

@Service
public class PostBoxService {

    @Autowired
    private PostBoxRepository postBoxRepository;

    @Autowired
    private UserRepository userRepository;


    public List<PostDto> getUnRepliedMessages(String tokenId){
        var entityUser = userRepository.findByTokenId(tokenId);
        if(entityUser == null) return Collections.emptyList();

        var post = postBoxRepository.findAllByTempUserIdAndRepliedFalseAndSentBy(entityUser.getId(),ROLE_LAWYER);
        return   post.stream().map(e -> {
           var postDto = new PostDto();
            postDto.setRefId(e.getId());
            postDto.setMessage(e.getMessage());
            postDto.setSent(e.getSent());
            return postDto;
        }).collect(Collectors.toList());
    }

    public boolean insertReplyFromTempUser(PostDto postDto) {
        var entityUser =  userRepository.findByTokenId(postDto.getTokenId());
        if(entityUser == null) return  false;

        var referencedPost = postBoxRepository.findByIdAndTempUserIdAndRepliedFalseAndSentBy(
                postDto.getRefId(), entityUser.getId(), ROLE_LAWYER);
        if(referencedPost != null){
            var reply = new PostboxPost();
            reply.setLawyerId(referencedPost.getLawyerId());
            reply.setMessage(postDto.getMessage());
            reply.setTempUserId(entityUser.getId());
            reply.setSentBy(ROLE_USER);
            reply.setReplied(false);
            reply.setSent(Date.from(new Date().toInstant()));
            postBoxRepository.save(reply);
                referencedPost.setReplied(true);
                postBoxRepository.save(referencedPost);
        }


      //  var post = postBoxRepository.
        return true;
    }
}
