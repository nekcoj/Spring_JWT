package com.whistleblower.app.modelDto;

public class MoveDto {
    private long lawyerId;
    private long issueId;

    public long getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(long lawyerId) {
        this.lawyerId = lawyerId;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }
}
