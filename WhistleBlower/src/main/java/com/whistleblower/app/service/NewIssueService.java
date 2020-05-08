package com.whistleblower.app.service;

import com.whistleblower.app.entity.NewIssue;
import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.modelDto.NewIssueDto;
import com.whistleblower.app.modelDto.TempUserDto;
import com.whistleblower.app.repository.NewIssueRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;

@Service
public class NewIssueService {


   private NewIssueRepository newIssueRepository;
    
   private UserRepository userRepository;

   private BCryptPasswordEncoder bCryptPasswordEncoder;

    public NewIssueService(NewIssueRepository newIssueRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.newIssueRepository = newIssueRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public TempUserDto createIssueAndUser(NewIssueDto newIssueDto) {
         TempUserDto tempUser = createTempUser();
         createNewIssue(tempUser, newIssueDto);
         return tempUser;
    }

    private void createNewIssue(TempUserDto tempUser, NewIssueDto newIssueDto) {
        NewIssue newIssue = new NewIssue();
                newIssue.setCategory(newIssueDto.getCategory());
                newIssue.setWhenIssue(newIssueDto.getWhenIssue());
                newIssue.setWhereIssue(newIssueDto.getWhereIssue());
                newIssue.setDetails(newIssueDto.getDetails());
                newIssue.setEmployeeAwareness(newIssueDto.getEmployeeAwareness());
                newIssue.setAttachment(newIssueDto.getAttachment());
                newIssue.setUserEntity(userRepository.getOne(tempUser.getId()));
                newIssue.setCreated(Date.from(new Date().toInstant()));
                newIssue.setIssueStatus("UNASSIGNED");
                newIssueRepository.save(newIssue);
    }

    private TempUserDto createTempUser() {
        UserEntity tempUserEntity = new UserEntity();

        String username = null;
        String password = null;
        while (username == null){
            String newUsername =  randomNumberGenerator();
            if(!userRepository.existsTempUserByUsername(newUsername)){
                username = newUsername;
                tempUserEntity.setUsername(username);
                password = randomNumberGenerator();
                tempUserEntity.setRole(ROLE_USER);
                tempUserEntity.setCreated(Date.from(new Date().toInstant()));
                tempUserEntity.setPassword(bCryptPasswordEncoder.encode(password));
                userRepository.save(tempUserEntity);
            }
        }
       return new TempUserDto(tempUserEntity.getId(),username,password);
    }


    private String randomNumberGenerator(){
        return  String.valueOf(Math.random()).replace("0.","").substring(0,8);
    }
}
