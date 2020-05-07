package com.whistleblower.app.service;

import com.whistleblower.app.entity.Admin;
import com.whistleblower.app.repository.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static java.util.Collections.emptyList;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
        private AdminRepository adminRepository;
        private  BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminDetailsServiceImpl(AdminRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    private void addDefaultUser(){
        var adminUser = adminRepository.findByUsernameIgnoreCase("Admin");
        if(adminUser == null){
            adminUser = new Admin();
            adminUser.setUsername("Admin");
            adminUser.setPassword(bCryptPasswordEncoder.encode("password"));
            adminRepository.save(adminUser);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin applicationUser = adminRepository.findByUsernameIgnoreCase(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

}
