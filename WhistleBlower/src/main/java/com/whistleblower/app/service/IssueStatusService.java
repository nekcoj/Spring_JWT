package com.whistleblower.app.service;

import com.whistleblower.app.entity.IssueStatus;
import com.whistleblower.app.repository.IssueStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueStatusService {


    @Autowired
    private IssueStatusRepository issueStatusRepository;


    public List<IssueStatus> getAll() {
        return issueStatusRepository.findAll();
    }
}
