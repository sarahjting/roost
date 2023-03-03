package com.sarahjting.roost.user.projections;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.projections.StorageBasicProjection;
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
    StorageBasicProjection defaultStorage;

    public UserBasicProjection(UUID id, String email, LocalDateTime createdAt, LocalDateTime lastActivityAt, Storage defaultStorage) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.lastActivityAt = lastActivityAt;
        this.defaultStorage = defaultStorage != null ? StorageBasicProjection.fromStorage(defaultStorage) : null;
    }

    static public UserBasicProjection fromUser(User user) {
        return new UserBasicProjection(user.getId(), user.getEmail(), user.getCreatedAt(), user.getLastActivityAt(), user.getDefaultStorage());
    }
}
