package com.whistleblower.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Issue {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long id;


    private String whenIssue;

    private String whereIssue;

    private String details;

    private String employeeAwareness;

    private String attachment;

    private Date created;
    private Date assigned;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne()
    @JoinColumn(name = "temp_user_id", referencedColumnName = "id", nullable = false)
    private UserEntity tempUser;

    @ManyToOne
    @JoinColumn(name = "lawyer_id", referencedColumnName = "id")
    private UserEntity lawyer;

   @ManyToOne
   @JoinColumn(name = "issue_status_id", referencedColumnName = "id")
    private IssueStatus issueStatus;

    public Category getCategory() {
        return category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public UserEntity getTempUser() {
        return tempUser;
    }

    public void setTempUser(UserEntity userEntity) {
        this.tempUser = userEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserEntity getLawyer() {
        return lawyer;
    }

    public void setLawyer(UserEntity lawyer) {
        this.lawyer = lawyer;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }
}
