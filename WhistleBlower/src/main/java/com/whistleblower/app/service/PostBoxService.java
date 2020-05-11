package com.whistleblower.app.service;

import com.whistleblower.app.repository.PostBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostBoxService {

    @Autowired
    private PostBoxRepository postBoxRepository;

}
