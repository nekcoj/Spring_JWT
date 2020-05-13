package com.whistleblower.app.repository;

import com.whistleblower.app.entity.PostboxPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostBoxRepository extends JpaRepository<PostboxPost, Long>{

}
