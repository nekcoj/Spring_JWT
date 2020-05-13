package com.whistleblower.app.modelDto;

public class NewIssueDto  {
    private long categoryId;
    private String whenIssue;
    private String whereIssue;
    private String details;
    private String employeeAwareness;
    private  String attachment;



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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }


}
