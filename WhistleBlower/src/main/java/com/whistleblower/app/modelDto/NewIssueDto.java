package com.whistleblower.app.modelDto;

import com.whistleblower.app.entity.Issue;

public class NewIssueDto  {
    private String category;
    private String when;
    private String where;
    private String details;
    private String employeeAwareness;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
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

    private String attachment;

}
