package com.whistleblower.app.repository;


import com.whistleblower.app.entity.AssignedIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedIssueRepository extends JpaRepository<AssignedIssue, Long> {


}