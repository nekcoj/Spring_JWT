package com.whistleblower.app.repository;



import com.whistleblower.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsTempUserByUsername(String username);

    User findByUsernameIgnoreCase(String username);
}