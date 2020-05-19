package com.whistleblower.app.modelDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whistleblower.app.entity.Issue;

import java.util.Date;

public class IssueDto {
    private long issueId;
    private long categoryId;
    private String whenIssue;
    private String whereIssue;
    private String details;
    private String employeeAwareness;
    private String attachmentFileName;
    private long tempUserId;
    private Date created;
    private Date assigned;
    private String issueStatus;

    public IssueDto() {
    }

    public IssueDto(Issue issue) {
       setIssueId(issue.getId());
       setCategoryId(issue.getCategory().getId());
       setWhenIssue(issue.getWhenIssue());
       setWhereIssue(issue.getWhereIssue());
       setDetails(issue.getDetails());
       setEmployeeAwareness(issue.getEmployeeAwareness());
       setCreated(issue.getCreated());
       setAssigned(issue.getAssigned());
       setTempUserId(issue.getTempUser().getId());
       setIssueStatus(issue.getIssueStatus().getStatus());
       setAttachmentFileName(issue.getAttachment());
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public long getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(long tempUserId) {
        this.tempUserId = tempUserId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getWhenIssue() {
        return whenIssue;
    }

    public void setWhenIssue(String whenIssue) {
        this.whenIssue = whenIssue;
    }

    public String getWhereIssue() {
        return whereIssue;
    }

    public void setWhereIssue(String whereIssue) {
        this.whereIssue = whereIssue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEmployeeAwareness() {
        return employeeAwareness;
    }

    public void setEmployeeAwareness(String employeeAwareness) {
        this.employeeAwareness = employeeAwareness;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }


}
