package com.whistleblower.app.modelDto;

public class StatusDto {
    private String tokenId;
    private  long issueId;
    private  long statusId;

    public long getIssueId() {
        return issueId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }
}
