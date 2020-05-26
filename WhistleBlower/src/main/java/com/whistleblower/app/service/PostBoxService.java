package com.whistleblower.app.service;

import com.whistleblower.app.entity.PostboxPost;
import com.whistleblower.app.modelDto.IssueAndPostDto;
import com.whistleblower.app.modelDto.IssueDto;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.repository.IssueRepository;
import com.whistleblower.app.repository.PostBoxRepository;
import com.whistleblower.app.repository.UserRepository;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;
import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;


@Service
public class PostBoxService {


    UserRepository userRepository;

    PostBoxRepository postBoxRepository;

    IssueRepository issueRepository;

    PooledPBEStringEncryptor encryptor;


    @Autowired
    public PostBoxService(UserRepository userRepository, PostBoxRepository postBoxRepository, IssueRepository issueRepository, StringEncryptor stringEncryptor) {
        this.userRepository = userRepository;
        this.postBoxRepository = postBoxRepository;
        this.issueRepository = issueRepository;
        this.encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);          // This would be a good value for a 4-core system
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    }

    public boolean sendMessageByLawyer(PostDto postDto, String name) {
        var lawyer = userRepository.findByUsername(name);
        boolean matchedIssue = issueRepository.existsByLawyer_IdAndTempUser_Id(lawyer.getId(),postDto.getTempUserId());
        //noinspection ConstantConditions
        if(lawyer != null && lawyer.getRole().equals(ROLE_LAWYER) && matchedIssue){
            var postMessage = new PostboxPost();
            if(postDto.getMessage() != null && postDto.getMessage().length() > 0){
                postMessage.setMessage(encryptor.encrypt(postDto.getMessage()));
            }else return false;
            postMessage.setLawyerId(lawyer.getId());
            postMessage.setSentDate(new Date());
            postMessage.setTempUserId(postDto.getTempUserId());
            postBoxRepository.save(postMessage);
            return true;
        }
        return false;

    }

    public boolean replyByTempUser(PostDto postDto, String name) {
        var tempUser = userRepository.findByUsername(name);
        var postMessage = postBoxRepository.findById(postDto.getPostboxId()).orElse(null);
        if(tempUser != null && postMessage != null && postMessage.getReply() == null
        && postMessage.getTempUserId() == tempUser.getId()){
            if(postDto.getMessage() != null && postDto.getMessage().length() > 0){
                postMessage.setReply(encryptor.encrypt(postDto.getMessage()));
            }else  return false;
            postMessage.setRepliedDate(new Date());
            postBoxRepository.save(postMessage);
            return  true;
        }
        return  false;
    }

    public List<PostboxPost> getMessagesForTempUser(String username) {
        var user = userRepository.findByUsername(username);
        if(user != null && user.getRole().equals(ROLE_USER)){
            return postBoxRepository.getAllByTempUserIdAndReplyIsNull(user.getId())
                    .stream()
                    .map(e -> {
                        if(e.getMessage() != null && e.getMessage().length() > 0){
                            e.setMessage(encryptor.decrypt(e.getMessage()));
                        }
                    return  e;
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Map<String, List<IssueAndPostDto>> getMessagesForLawyer(String username) {
        var lawyer = userRepository.findByUsername(username);

        if(lawyer != null && lawyer.getRole().equals(ROLE_LAWYER)){
            return issueRepository.findByLawyer_Id(lawyer.getId())
                    .stream()
                    .map(issue -> {
                        IssueDto issueDto = new IssueDto(issue);
               IssueAndPostDto issueAndPostDto = new IssueAndPostDto();
               issueAndPostDto.setIssue(issueDto);
               var messages = postBoxRepository.getAllByLawyerIdAndTempUserId(lawyer.getId(), issue.getTempUser().getId()).stream().map(e -> {
                            if(e.getMessage() != null && e.getMessage().length() > 0){
                                e.setMessage(encryptor.decrypt(e.getMessage()));
                            }
                            if(e.getReply() != null && e.getReply().length() > 0){
                             e.setReply(encryptor.decrypt(e.getReply()));
                              }
                            return e;
                            }).collect(Collectors.toList());


               issueAndPostDto.setMessages(messages);
               return issueAndPostDto;
           }).collect(Collectors.groupingBy(e -> "issue" + e.getIssue().getIssueId(),
                            Collectors.toList()));
        }
        return Collections.emptyMap();
    }
}
