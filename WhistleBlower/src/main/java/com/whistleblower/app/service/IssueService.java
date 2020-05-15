package com.whistleblower.app.service;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.modelDto.*;
import com.whistleblower.app.repository.CategoryRepository;
import com.whistleblower.app.repository.IssueRepository;
import com.whistleblower.app.repository.IssueStatusRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;
import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;

@Service
public class IssueService {


    private IssueRepository issueRepository;

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    private IssueStatusRepository issueStatusRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public IssueService(IssueRepository issueRepository, UserRepository userRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder, IssueStatusRepository issueStatusRepository, CategoryRepository categoryRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.issueStatusRepository = issueStatusRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.categoryRepository = categoryRepository;
    }

    public TempUserDto createIssueAndUser(IssueDto issueDto) {
        TempUserDto tempUser = createTempUser();
        createNewIssue(tempUser, issueDto);
        return tempUser;
    }

    private void createNewIssue(TempUserDto tempUser, IssueDto issueDto) {
        var newIssue = new Issue();
        var category = categoryRepository.findById(issueDto.getCategoryId()).orElse(null);
        newIssue.setCategory(category);
        newIssue.setWhenIssue(issueDto.getWhenIssue());
        newIssue.setWhereIssue(issueDto.getWhereIssue());
        newIssue.setDetails(issueDto.getDetails());
        newIssue.setEmployeeAwareness(issueDto.getEmployeeAwareness());
        newIssue.setAttachment(issueDto.getAttachment());
        newIssue.setTempUser(userRepository.getOne(tempUser.getId()));
        newIssue.setCreated(new Date());
        newIssue.setIssueStatus(issueStatusRepository.getOne(1L));
        newIssue.setActive(true);
        issueRepository.save(newIssue);
    }

    private TempUserDto createTempUser() {
        UserEntity tempUserEntity = new UserEntity();

        String username = null;
        String password = null;
        while (username == null) {
            String newUsername = randomNumberGenerator();
            if (!userRepository.existsTempUserByUsername(newUsername)) {
                username = newUsername;
                tempUserEntity.setUsername(username);
                password = randomNumberGenerator();
                tempUserEntity.setEnabled(true);
                tempUserEntity.setRole(ROLE_USER);
                tempUserEntity.setCreated(new Date());
                tempUserEntity.setPassword(bCryptPasswordEncoder.encode(password));
                userRepository.save(tempUserEntity);
            }
        }
        return new TempUserDto(tempUserEntity.getId(), username, password);
    }


    private String randomNumberGenerator() {
        return String.valueOf(Math.random()).replace("0.", "").substring(0, 8);
    }

    public List<IssueDto> getAllIssuesForAdmin() {
        return issueRepository.findAll().stream().map(IssueDto::new).collect(Collectors.toList());
    }

    //Admin
    public boolean assignIssue(AssignDto assignDto) {
        var issueToAssign = issueRepository.findById(assignDto.getIssueId());
        var assignedLawyer = userRepository.findById(assignDto.getLawyerId());

        if (issueToAssign.isPresent() && assignedLawyer.isPresent() && assignedLawyer.get().getRole().equals(ROLE_LAWYER)) {
            var issue = issueToAssign.get();
            var lawyer = assignedLawyer.get();
            var status = issueStatusRepository.findById(2L);
            status.ifPresent(issue::setIssueStatus);
            issue.setLawyer(lawyer);
            issue.setAssigned(new Date());
            issueRepository.save(issue);
            return true;
        }
        return false;
    }

    //Lawyer
    public boolean changeIssueStatus(StatusDto statusDto, Authentication authentication) {
        var user = userRepository.findByUsername(authentication.getName());
        var issue = issueRepository.findById(statusDto.getIssueId());
        if (issue.isPresent() && user != null && user.getId() == issue.get().getLawyer().getId()
        && user.getRole().equals(ROLE_LAWYER)) {
            var issueToChange = issue.get();
            var issueStatus = issueStatusRepository.findById(statusDto.getStatusId());

            if (issueStatus.isPresent()) {
                issueToChange.setIssueStatus(issueStatus.get());
                issueRepository.save(issueToChange);
                return true;
            }
        }
        return false;
    }


   public List<IssueDto> getIssuesForLawyer(TokenId tokenId){
        var lawyer  = userRepository.findByTokenId(tokenId.getTokenId());
        if(lawyer != null && lawyer.getRole().equals(ROLE_LAWYER)){
            return issueRepository.findByLawyer_Id(lawyer.getId()).stream().map(IssueDto::new).collect(Collectors.toList());

        }
        return Collections.emptyList();
    }

}
