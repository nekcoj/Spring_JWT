package com.whistleblower.app.repository;


import com.whistleblower.app.entity.Category;
import com.whistleblower.app.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}