package com.whistleblower.app.modelDto;

public class LoginResponse {
    private String token;
    private String path;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
