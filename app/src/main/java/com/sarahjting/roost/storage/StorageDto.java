package com.sarahjting.roost.storage;

import com.sarahjting.roost.storage.validation.StorageDtoAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@StorageDtoAnnotation
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageDriver getDriver() {
        return driver;
    }

    public void setDriver(StorageDriver driver) {
        this.driver = driver;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
