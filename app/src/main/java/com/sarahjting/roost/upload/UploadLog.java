package com.sarahjting.roost.upload;

import com.sarahjting.roost.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "upload_logs", indexes = {
    @Index(name="upload_logs_createdAt", columnList = "upload_id, created_at"),
    @Index(name="upload_logs_versionId", columnList = "upload_id, version_id"),
})
@Getter
@Setter
public class UploadLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "upload_id", nullable = false)
    @NotNull
    private Upload upload;

    @Column(name = "version_id", unique = true)
    @NotNull
    private String versionId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UploadLog()
    {
    }

    public UploadLog(User user, Upload upload, String versionId) {
        this.user = user;
        this.upload = upload;
        this.versionId = versionId;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadLog uploadLog = (UploadLog) o;
        return Objects.equals(id, uploadLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UploadLog{" +
                "upload=" + upload +
                ", versionId='" + versionId + '\'' +
                '}';
    }

    public String getFileUrl() {
        return String.format("%s?versionId=%s", this.upload.getFileUrl(), versionId);
    }
}
