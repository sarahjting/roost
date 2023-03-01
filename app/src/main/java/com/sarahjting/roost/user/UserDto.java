package com.sarahjting.roost.user;

import com.sarahjting.roost.common.validation.Password;
import com.sarahjting.roost.common.validation.UniqueInDatabase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull(message = "Email address must be provided.")
    @Email(message = "This is not a valid email address.")
    @UniqueInDatabase(
        message = "This email address has already been used. If you have already registered, please try signing in instead.",
        entity = User.class,
        attributeName = "email"
    )
    private String email;

    @NotNull(message = "Email address must be provided.")
    @Password(message = "Password must contain at least 3 upper-case letters, symbols, or numbers.")
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
