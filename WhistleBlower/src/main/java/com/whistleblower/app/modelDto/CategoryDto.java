package com.whistleblower.app.modelDto;

import org.springframework.validation.BindingResult;

public class CategoryDto {
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
