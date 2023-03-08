package com.sarahjting.roost.user.projections;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import java.time.LocalDateTime;
import java.util.UUID;

public interface UserBasicProjection {
    UUID getId();
    String getUsername();
    String getEmail();
    LocalDateTime getCreatedAt();
    LocalDateTime getLastActivityAt();
    StorageBasicProjection getDefaultStorage();
}
