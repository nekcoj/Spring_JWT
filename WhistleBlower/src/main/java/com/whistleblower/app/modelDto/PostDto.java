package com.whistleblower.app.modelDto;

import java.util.Date;

public class PostDto {
    private long lawyerId;
    private String message;
    private String tokenId;
    private long refId;
    private Date sent;

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public long getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(long lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
