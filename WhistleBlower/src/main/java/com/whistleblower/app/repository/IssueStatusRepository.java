package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Issue;
import com.whistleblower.app.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueStatusRepository extends JpaRepository<IssueStatus, Long> {


}