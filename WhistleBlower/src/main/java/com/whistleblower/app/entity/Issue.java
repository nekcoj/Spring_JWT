package com.whistleblower.app.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Issue {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;

    private String category;

    private String when;

    private String where;

    private String details;

    private String employeeAwareness;

    private String attachment;

    @OneToOne
    private TempUser tempUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    private String issueStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public User getTempUser() {
        return tempUser;
    }

    public void setTempUser(TempUser tempUser) {
        this.tempUser = tempUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
}
