package com.whistleblower.app.repository;



import com.whistleblower.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    boolean existsTempUserByUsername(String username);

    UserEntity findByUsernameIgnoreCase(String username);
}