package com.whistleblower.app.rest;

import com.whistleblower.app.modelDto.CategoryDto;
import com.whistleblower.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static com.whistleblower.app.security.SecurityConstants.*;

@RestController
@RequestMapping(CATEGORY_URL_ROOT)
public class CategoryController {

@Autowired
private CategoryService categoryService;


@PostMapping(ADD_CATEGORY)
ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto categoryDto,
                              BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(categoryDto);
    }
  boolean added = categoryService.addCategory(categoryDto);
    if(added){
        return  ResponseEntity.ok("Category Added!");
    }else {
        return ResponseEntity.badRequest().body(categoryDto);
    }
}

    @PostMapping(REMOVE_CATEGORY)
    ResponseEntity<?> removeCategory(@Valid @RequestBody CategoryDto categoryDto,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(categoryDto);
        }
        boolean removed = categoryService.removeCategory(categoryDto);
        if(removed){
            return  ResponseEntity.ok("Category Removed!");
        }else {
            return ResponseEntity.badRequest().body(categoryDto);
        }
    }

}
