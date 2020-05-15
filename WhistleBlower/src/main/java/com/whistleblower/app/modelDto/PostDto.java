package com.whistleblower.app.modelDto;

public class PostDto {
    private String message;
    private long postboxId;
    private long tempUserId;


    public long getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(long tempUserId) {
        this.tempUserId = tempUserId;
    }

    public long getPostboxId() {
        return postboxId;
    }

    public void setPostboxId(long postboxId) {
        this.postboxId = postboxId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
