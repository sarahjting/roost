package com.sarahjting.roost.upload;

import com.sarahjting.roost.common.persistence.converters.JsonToMapConverter;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;
import com.sarahjting.roost.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "uploads", indexes = {
    @Index(name="uploads_createdAt", columnList = "created_at"),
    @Index(name="uploads_user_type", columnList = "user_id, created_at, type"),
    @Index(name="uploads_storage", columnList = "storage_id, created_at")
})
public class Upload {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @Column(name = "type", nullable = false)
    private UploadType type;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "original_file_size", nullable = false)
    private String originalFileSize;

    @Column(name = "original_mime_type", nullable = false)
    private String originalMimeType;

    @Column(name = "original_extension", nullable = false)
    private String originalExtension;

    @Column(name = "original_image_width")
    private Long originalImageWidth;

    @Column(name = "original_image_height")
    private Long originalImageHeight;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Upload(User user, Storage storage, UploadType type, String fileName, String originalFileName, String originalFileSize, String originalMimeType, String originalExtension) {
        this.user = user;
        this.storage = storage;
        this.type = type;
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.originalFileSize = originalFileSize;
        this.originalMimeType = originalMimeType;
        this.originalExtension = originalExtension;
        this.createdAt = LocalDateTime.now();
    }

    public Upload(User user, Storage storage, UploadType type, String fileName, String originalFileName, String originalFileSize, String originalMimeType, String originalExtension, Long originalImageWidth, Long originalImageHeight) {
        this.user = user;
        this.storage = storage;
        this.type = type;
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.originalFileSize = originalFileSize;
        this.originalMimeType = originalMimeType;
        this.originalExtension = originalExtension;
        this.originalImageWidth = originalImageWidth;
        this.originalImageHeight = originalImageHeight;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public UploadType getType() {
        return type;
    }

    public void setType(UploadType type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getOriginalFileSize() {
        return originalFileSize;
    }

    public void setOriginalFileSize(String originalFileSize) {
        this.originalFileSize = originalFileSize;
    }

    public String getOriginalMimeType() {
        return originalMimeType;
    }

    public void setOriginalMimeType(String originalMimeType) {
        this.originalMimeType = originalMimeType;
    }

    public String getOriginalExtension() {
        return originalExtension;
    }

    public void setOriginalExtension(String originalExtension) {
        this.originalExtension = originalExtension;
    }

    public Long getOriginalImageWidth() {
        return originalImageWidth;
    }

    public void setOriginalImageWidth(Long originalImageWidth) {
        this.originalImageWidth = originalImageWidth;
    }

    public Long getOriginalImageHeight() {
        return originalImageHeight;
    }

    public void setOriginalImageHeight(Long originalImageHeight) {
        this.originalImageHeight = originalImageHeight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Upload upload = (Upload) o;
        return Objects.equals(id, upload.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", user=" + user +
                ", type=" + type +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
