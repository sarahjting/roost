package com.sarahjting.roost.user;

import com.sarahjting.roost.common.validation.Password;
import com.sarahjting.roost.common.validation.UniqueInDatabase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotNull(message = "Username must be provided.")
    @Pattern(regexp = "^[\\p{Alnum}]{1,32}$", message = "Username must be alphanumeric and between 1-32 characters long.")
    @UniqueInDatabase(
        message = "This username has already been used. If you have already registered, please try signing in instead.",
        entity = User.class,
        attributeName = "username"
    )
    private String username;

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
}
