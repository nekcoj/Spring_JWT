package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Admin;
import com.whistleblower.app.entity.ClosedIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosedIssueRepository extends JpaRepository<ClosedIssue, Long> {


}