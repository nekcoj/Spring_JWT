package com.whistleblower.app.repository;

import com.whistleblower.app.entity.PostboxPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostBoxRepository extends JpaRepository<PostboxPost, Long>{
    List<PostboxPost> findAllByTempUserIdAndRepliedFalse(long tempUserId);
}
