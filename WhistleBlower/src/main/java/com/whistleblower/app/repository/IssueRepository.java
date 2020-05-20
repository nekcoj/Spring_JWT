package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

  List<Issue> findByLawyer_Id(long lawyer_id);

  boolean existsByLawyer_IdAndTempUser_Id(long lawyer_id, long tempUser_id);

  Issue findByTempUser_Id(long tempUser_id);

  Issue getIssueByIdAndLawyer_Username(long id, String lawyer_username);

}