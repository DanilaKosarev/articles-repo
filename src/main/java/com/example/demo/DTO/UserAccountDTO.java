package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserAccountDTO {

    @NotBlank(message = "Username should not be null or empty")
    private String username;

    @NotBlank(message = "Password should not be null or empty")
    private String password;

    public UserAccountDTO() {
    }

    public UserAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
