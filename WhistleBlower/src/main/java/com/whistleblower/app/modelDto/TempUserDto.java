package com.whistleblower.app.modelDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TempUserDto {

    private  String username;
    private String password;
    @JsonIgnore
    private long id;

    public TempUserDto() {
    }

    public TempUserDto(long id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
