package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Admin;
import com.whistleblower.app.entity.NewIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewIssueRepository extends JpaRepository<NewIssue, Long> {


}