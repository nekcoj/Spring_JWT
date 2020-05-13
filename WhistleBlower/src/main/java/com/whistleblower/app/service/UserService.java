package com.whistleblower.app.service;

import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<UserEntity> getAllLawyers() {
        return userRepository.findAllByRole(ROLE_LAWYER);
    }
}
