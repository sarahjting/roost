package com.sarahjting.roost.user.projections;

import com.sarahjting.roost.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(LocalDateTime lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

}
