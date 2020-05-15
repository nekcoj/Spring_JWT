package com.whistleblower.app.service;

import com.whistleblower.app.entity.PostboxPost;
import com.whistleblower.app.modelDto.IssueAndPostDto;
import com.whistleblower.app.modelDto.IssueDto;
import com.whistleblower.app.modelDto.PostDto;
import com.whistleblower.app.repository.IssueRepository;
import com.whistleblower.app.repository.PostBoxRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;
import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;


@Service
public class PostBoxService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostBoxRepository postBoxRepository;

    @Autowired
    IssueRepository issueRepository;


    public boolean sendMessageByLawyer(PostDto postDto, String name) {
        var lawyer = userRepository.findByUsername(name);
        boolean matchedIssue = issueRepository.existsByLawyer_IdAndTempUser_Id(lawyer.getId(),postDto.getTempUserId());
        //noinspection ConstantConditions
        if(lawyer != null && lawyer.getRole().equals(ROLE_LAWYER) && matchedIssue){
            var postMessage = new PostboxPost();
            postMessage.setMessage(postDto.getMessage());
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
            postMessage.setReply(postDto.getMessage());
            postMessage.setRepliedDate(new Date());
            postBoxRepository.save(postMessage);
            return  true;
        }
        return  false;
    }

    public List<PostboxPost> getMessagesForTempUser(String username) {
        var user = userRepository.findByUsername(username);
        if(user != null && user.getRole().equals(ROLE_USER)){
            return postBoxRepository.getAllByTempUserIdAndReplyIsNull(user.getId());
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
               var messages = postBoxRepository.getAllByLawyerIdAndTempUserId(lawyer.getId(), issue.getTempUser().getId());
               issueAndPostDto.setMessages(messages);
               return issueAndPostDto;
           }).collect(Collectors.groupingBy(e -> "issue" + e.getIssue().getIssueId(),
                            Collectors.toList()));
        }
        return Collections.emptyMap();
    }
}
