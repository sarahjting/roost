package com.sarahjting.roost.upload;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "uploads", indexes = {
    @Index(name="uploads_createdAt", columnList = "created_at"),
    @Index(name="uploads_user_type", columnList = "user_id, created_at, type"),
    @Index(name="uploads_storage", columnList = "storage_id, created_at")
})
@Getter
@Setter
public class Upload {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    @NotNull
    private Storage storage;

    @Column(name = "type", nullable = false)
    @NotNull
    private UploadType type;

    @Column(name = "file_name", nullable = false)
    @NotBlank
    private String fileName;

    @Column(name = "file_size", nullable = false)
    @NotNull
    @Min(1)
    private Long fileSize;

    @Column(name = "mime_type", nullable = false)
    @NotNull
    private String mimeType;

    @Column(name = "image_width")
    private Long imageWidth;

    @Column(name = "image_height")
    private Long imageHeight;

    @Column(name = "original_file_name", nullable = false)
    @NotBlank
    private String originalFileName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Upload()
    {
    }

    public Upload(User user, Storage storage, UploadType type, String fileName, Long fileSize, String mimeType, Long imageWidth, Long imageHeight, String originalFileName) {
        this.user = user;
        this.storage = storage;
        this.type = type;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.originalFileName = originalFileName;
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

    public String getFilePath() {
        return String.format("files/{0}", this.getFileName());
    }

}
