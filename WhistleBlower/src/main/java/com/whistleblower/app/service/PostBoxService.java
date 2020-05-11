package com.whistleblower.app.service;

import com.whistleblower.app.entity.PostboxPost;
import com.whistleblower.app.modelDto.TokenId;
import com.whistleblower.app.repository.PostBoxRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PostBoxService {

    @Autowired
    private PostBoxRepository postBoxRepository;

    @Autowired
    private UserRepository userRepository;


    public List<PostboxPost> getUnRepliedMessages(String tokenId){
        var entityUser = userRepository.findByTokenId(tokenId);
      return postBoxRepository.findAllByTempUserIdAndRepliedFalse(entityUser.getId());
    }

}
