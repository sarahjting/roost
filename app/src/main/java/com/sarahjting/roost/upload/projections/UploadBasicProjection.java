package com.sarahjting.roost.upload.projections;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.projections.StorageBasicProjection;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.upload.UploadType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UploadBasicProjection {
    UUID id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    StorageBasicProjection storage;
    UploadType type;
    String fileName;
    Long fileSize;
    String mimeType;
    Long imageWidth;
    Long imageHeight;
    String originalFileName;

    public UploadBasicProjection(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, Storage storage, UploadType type, String fileName, Long fileSize, String mimeType, Long imageWidth, Long imageHeight, String originalFileName) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.storage = StorageBasicProjection.fromStorage(storage);
        this.type = type;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.originalFileName = originalFileName;
    }

    public static UploadBasicProjection fromUpload(Upload upload) {
        return new UploadBasicProjection(
            upload.getId(),
            upload.getCreatedAt(),
            upload.getUpdatedAt(),
            upload.getStorage(),
            upload.getType(),
            upload.getFileName(),
            upload.getFileSize(),
            upload.getMimeType(),
            upload.getImageWidth(),
            upload.getImageHeight(),
            upload.getOriginalFileName()
        );
    }
}
