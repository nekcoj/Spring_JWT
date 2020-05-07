package com.whistleblower.app.service;

import com.whistleblower.app.entity.NewIssue;
import com.whistleblower.app.entity.TempUser;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.TempUserDto;
import com.whistleblower.app.repository.AdminRepository;
import com.whistleblower.app.repository.NewIssueRepository;
import com.whistleblower.app.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NewIssueService {

    @Autowired
    NewIssueRepository newIssueRepository;
    
    @Autowired
    TempUserRepository tempUserRepository;


    public TempUserDto createIssueAndUser(NewIssueDto newIssueDto) {
        TempUser tempUser = createTempUser();
         createNewIssue(tempUser, newIssueDto);
         return new TempUserDto(tempUser.getUsername(), tempUser.getPassword());
    }

    private void createNewIssue(TempUser tempUser, NewIssueDto newIssueDto) {
        NewIssue newIssue = new NewIssue();
                newIssue.setCategory(newIssueDto.getCategory());
                newIssue.setWhen(newIssueDto.getWhen());
                newIssue.setWhere(newIssueDto.getWhere());
                newIssue.setDetails(newIssueDto.getDetails());
                newIssue.setEmployeeAwareness(newIssueDto.getEmployeeAwareness());
                newIssue.setAttachment(newIssueDto.getAttachment());
                newIssue.setTempUser(tempUser);
                newIssue.setIssueStatus("UNASSIGNED");
                newIssueRepository.save(newIssue);
    }

    private TempUser createTempUser() {
        TempUser tempUser = new TempUser();
        String username = null;
        while (username == null){
            String newUsername =  randomNumberGenerator();
            if(!tempUserRepository.existsTempUserByUsername(newUsername)){
                username = newUsername;
                tempUser.setUsername(username);
                tempUser.setPassword(randomNumberGenerator());
            }
        }
       return tempUserRepository.save(tempUser);
    }


    private String randomNumberGenerator(){
        return  String.valueOf(Math.random()).replace("0.","").substring(0,8);
    }
}
