package com.whistleblower.app.service;

import com.whistleblower.app.entity.Category;
import com.whistleblower.app.modelDto.CategoryDto;
import com.whistleblower.app.repository.CategoryRepository;
import com.whistleblower.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.whistleblower.app.security.SecurityConstants.ROLE_ADMIN;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    public boolean addCategory(CategoryDto categoryDto, String username) {
         var user = userRepository.findByUsername(username);
         if(user != null && user.getRole().equals(ROLE_ADMIN)
                 && !categoryDto.getCategoryName().isEmpty()){
             Category category = new Category();
             category.setCategoryName(categoryDto.getCategoryName());
             categoryRepository.save(category);
             return  true;
         }
         return false;
    }

    public boolean removeCategory(CategoryDto categoryDto, String username) {
        var user = userRepository.findByUsername(username);
        if(user != null && user.getRole().equals(ROLE_ADMIN)){
         var category =  categoryRepository.findByCategoryName(categoryDto.getCategoryName());
         if(category != null){
             categoryRepository.deleteById(category.getId());
             return true;
         }
        }
        return false;
    }

    public List<Category> getAll() {
       return categoryRepository.findAll();
    }
}
