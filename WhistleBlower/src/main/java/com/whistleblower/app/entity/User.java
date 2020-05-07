package com.whistleblower.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class User {


    private @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)  long id;

    private String username;
    private String password;
    private Date lastLogin;
    private String tokenId;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenID) {
        this.tokenId = tokenID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
