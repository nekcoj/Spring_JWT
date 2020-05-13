package com.whistleblower.app.service;

import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Collection;
import java.util.Date;

import static com.whistleblower.app.security.SecurityConstants.ROLE_ADMIN;
import static com.whistleblower.app.security.SecurityConstants.ROLE_LAWYER;
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
        var adminUser = userRepository.findByUsernameIgnoreCase(ROLE_ADMIN);
        if(adminUser == null){
            adminUser = new UserEntity();
            adminUser.setRole(ROLE_ADMIN);
            adminUser.setCreated(Date.from(new Date().toInstant()));
            adminUser.setUsername("Admin");
            adminUser.setPassword(bCryptPasswordEncoder.encode("password"));
            userRepository.save(adminUser);
        }

        var lawyerUser = userRepository.findByUsernameIgnoreCase(ROLE_LAWYER);
        if(lawyerUser == null){
            lawyerUser = new UserEntity();
            lawyerUser.setRole(ROLE_LAWYER);
            lawyerUser.setCreated(Date.from(new Date().toInstant()));
            lawyerUser.setUsername("Lawyer");
            lawyerUser.setPassword(bCryptPasswordEncoder.encode("password"));
            userRepository.save(lawyerUser);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
        String[] userRoles = {user.getRole()};
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
