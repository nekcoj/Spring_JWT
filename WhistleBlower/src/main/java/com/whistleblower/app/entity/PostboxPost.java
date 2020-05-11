package com.whistleblower.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class PostboxPost {
    private @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)  long id;

    private Date sent;
    private int tempUserId;
    private int lawyerId;
    private String message;
    private String sentBy;
    private boolean replied;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public int getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(int tempUserId) {
        this.tempUserId = tempUserId;
    }

    public int getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(int lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public boolean isReplied() {
        return replied;
    }

    public void setReplied(boolean replied) {
        this.replied = replied;
    }
}
