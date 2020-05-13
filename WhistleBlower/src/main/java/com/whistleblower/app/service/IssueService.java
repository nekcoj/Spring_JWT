package com.whistleblower.app.service;

import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.modelDto.AssignDto;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.StatusDto;
import com.whistleblower.app.modelDto.TempUserDto;
import com.whistleblower.app.repository.CategoryRepository;
import com.whistleblower.app.repository.IssueRepository;
import com.whistleblower.app.repository.IssueStatusRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public TempUserDto createIssueAndUser(NewIssueDto newIssueDto) {
        TempUserDto tempUser = createTempUser();
        createNewIssue(tempUser, newIssueDto);
        return tempUser;
    }

    private void createNewIssue(TempUserDto tempUser, NewIssueDto newIssueDto) {
        var newIssue = new Issue();
        var category = categoryRepository.findById(newIssueDto.getCategoryId()).orElse(null);
        newIssue.setCategory(category);
        newIssue.setWhenIssue(newIssueDto.getWhenIssue());
        newIssue.setWhereIssue(newIssueDto.getWhereIssue());
        newIssue.setDetails(newIssueDto.getDetails());
        newIssue.setEmployeeAwareness(newIssueDto.getEmployeeAwareness());
        newIssue.setAttachment(newIssueDto.getAttachment());
        newIssue.setTempUser(userRepository.getOne(tempUser.getId()));
        newIssue.setCreated(Date.from(new Date().toInstant()));
        newIssue.setIssueStatus(issueStatusRepository.getOne(1L));
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
                tempUserEntity.setRole(ROLE_USER);
                tempUserEntity.setCreated(Date.from(new Date().toInstant()));
                tempUserEntity.setPassword(bCryptPasswordEncoder.encode(password));
                userRepository.save(tempUserEntity);
            }
        }
        return new TempUserDto(tempUserEntity.getId(), username, password);
    }


    private String randomNumberGenerator() {
        return String.valueOf(Math.random()).replace("0.", "").substring(0, 8);
    }

    public List<Issue> getAllNewIssues() {
        return issueRepository.findAll();
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
            issueRepository.save(issue);
            return true;
        }
        return false;
    }

    //Lawyer
    public boolean changeIssueStatus(StatusDto statusDto) {
        var user = userRepository.findByTokenId(statusDto.getTokenId());
        var issue = issueRepository.findById(statusDto.getIssueId());
        if (issue.isPresent() && user != null && user.getId() == issue.get().getLawyer().getId()) {
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
}
