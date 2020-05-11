package com.whistleblower.app.modelDto;



public class PostDto {
    private String tokenId;
    private  int refId;
    private String message;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }



    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
