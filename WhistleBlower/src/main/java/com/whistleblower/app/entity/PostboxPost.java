package com.whistleblower.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date sentDate;

    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date repliedDate;

    private long tempUserId;
    private long lawyerId;
    private String message;
    private String reply;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sent) {
        this.sentDate = sent;
    }

    public long getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(long tempUserId) {
        this.tempUserId = tempUserId;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(Date repliedDate) {
        this.repliedDate = repliedDate;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
