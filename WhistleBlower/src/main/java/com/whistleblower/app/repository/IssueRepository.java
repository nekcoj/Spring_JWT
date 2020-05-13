package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

  List<Issue> findByLawyer_Id(long lawyer_id);
}