package com.sarahjting.roost.upload.projections;

import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.upload.UploadType;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UploadBasicProjection {
    UUID getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    StorageBasicProjection getStorage();
    UploadType getType();
    String getFileName();
    Long getFileSize();
    String getMimeType();
    Long getImageWidth();
    Long getImageHeight();
    String getOriginalFileName();
    String getOriginalFileExtension();

    // the projection will only have access to the entity getters/setters on derived queries!!!
    // if you use JPQL it returns an anonymous map instead of an entity

    @Value("#{target.getFileNameAndExtension()}")
    String getFileNameAndExtension();

    @Value("#{target.getFileUrl()}")
    String getFileUrl();
}
