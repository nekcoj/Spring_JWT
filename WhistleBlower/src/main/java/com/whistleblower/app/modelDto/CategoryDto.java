package com.whistleblower.app.modelDto;



public class CategoryDto {

    public CategoryDto(){}

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
