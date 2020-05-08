package com.whistleblower.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Issue {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;

    private String category;

    private String whenIssue;

    private String whereIssue;

    private String details;

    private String employeeAwareness;

    private String attachment;

    @OneToOne()
    @JoinColumn(name = "temp_user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    private Date created;

    private String issueStatus;


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

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

    public String getWhenIssue() {
        return whenIssue;
    }

    public void setWhenIssue(String when) {
        this.whenIssue = when;
    }

    public String getWhereIssue() {
        return whereIssue;
    }

    public void setWhereIssue(String where) {
        this.whereIssue = where;
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
