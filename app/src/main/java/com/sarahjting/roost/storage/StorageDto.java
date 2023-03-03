package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.validation.StorageDtoAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@StorageDtoAnnotation
@Getter
@Setter
public class StorageDto {

    @NotBlank(message = "Name must be provided.")
    private String name;

    @NotNull(message = "Driver must be provided.")
    private StorageDriver driver;

    // either the endpoint or the region can be provided; the endpoint can be derived from the driver and region
    private String endpoint;

    private String region;

    @NotBlank(message = "Access key must be provided.")
    private String accessKey;

    @NotBlank(message = "Secret key must be provided.")
    private String secretKey;

    @NotBlank(message = "Bucket must be provided.")
    private String bucketName;

    public StorageDto() {}

    public StorageDto(String name, StorageDriver driver, String endpoint, String region, String accessKey, String secretKey, String bucketName) {
        this.name = name;
        this.driver = driver;
        this.endpoint = endpoint;
        this.region = region;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }
}
