package com.sarahjting.roost.storage;

import com.sarahjting.roost.common.persistence.converters.JsonToMapConverter;
import com.sarahjting.roost.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "storages", indexes = {
    @Index(name = "storages_user_name", columnList = "user_id, name")
})
@Getter
@Setter
public class Storage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "driver", nullable = false)
    @NotNull
    private StorageDriver driver;

    @Column(name = "endpoint", nullable = false)
    @NotBlank
    private String endpoint;

    @Column(name = "access_key", nullable = false)
    @NotBlank
    private String accessKey;

    @Column(name = "secret_key", nullable = false)
    @NotBlank
    private String secretKey;

    @Column(name = "bucket_name", nullable = false)
    @NotBlank
    private String bucketName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_activity_at")
    private LocalDateTime lastActivityAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "metadata")
    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Object> metadata = new HashMap<>();

    public Storage(User user, String name, StorageDriver driver, String endpoint, String accessKey, String secretKey, String bucketName) {
        this.user = user;
        this.name = name;
        this.driver = driver;
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }

    public Storage(User user, String name, StorageDriver driver, String endpoint, String accessKey, String secretKey, String bucketName, Map<String, Object> metadata) {
        this.user = user;
        this.name = name;
        this.driver = driver;
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
        this.metadata = metadata;
    }

    public Storage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", driver=" + driver +
                '}';
    }
}
