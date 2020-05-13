package com.whistleblower.app.modelDto;

import org.springframework.validation.BindingResult;

public class CategoryDto {
    private String tokenId;
    private String categoryName;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
