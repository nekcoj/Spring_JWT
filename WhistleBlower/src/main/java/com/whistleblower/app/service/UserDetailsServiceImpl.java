package com.whistleblower.app.service;

import com.whistleblower.app.entity.User;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Date;

import static com.whistleblower.app.security.SecurityConstants.ROLE_ADMIN;
import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
        private UserRepository userRepository;
        private  BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    private void addDefaultUser(){
        var adminUser = userRepository.findByUsernameIgnoreCase("Admin");
        if(adminUser == null){
            adminUser = new User();
            adminUser.setRole(ROLE_ADMIN);
            adminUser.setCreated(Date.from(new Date().toInstant()));
            adminUser.setUsername("Admin");
            adminUser.setPassword(bCryptPasswordEncoder.encode("password"));
            userRepository.save(adminUser);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = userRepository.findByUsernameIgnoreCase(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

}
