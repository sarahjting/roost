package com.sarahjting.roost.user;

import com.sarahjting.roost.common.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @Email
    @NotBlank
    private String email;

    @Password
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
