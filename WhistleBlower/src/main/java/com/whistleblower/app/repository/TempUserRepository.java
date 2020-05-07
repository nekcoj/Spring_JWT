package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Admin;
import com.whistleblower.app.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {


    boolean existsTempUserByUsername(String username);

}