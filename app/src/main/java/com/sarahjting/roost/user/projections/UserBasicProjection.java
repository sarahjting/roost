package com.sarahjting.roost.user.projections;

import com.sarahjting.roost.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserBasicProjection {
    UUID id;
    String email;
    LocalDateTime createdAt;
    LocalDateTime lastActivityAt;

    public UserBasicProjection(UUID id, String email, LocalDateTime createdAt, LocalDateTime lastActivityAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.lastActivityAt = lastActivityAt;
    }

    static public UserBasicProjection fromUser(User user) {
        return new UserBasicProjection(user.getId(), user.getEmail(), user.getCreatedAt(), user.getLastActivityAt());
    }
}
