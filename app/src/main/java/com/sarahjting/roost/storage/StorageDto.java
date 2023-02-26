package com.sarahjting.roost.storage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StorageDto {
    @NotBlank
    private String name;

    @NotNull
    private StorageDriver driver;

    private String b2AppKey;

    private String b2AppKeyId;

    private String b2BucketName;

    public StorageDto(String name, StorageDriver driver, String b2ApiKey, String b2ApiKeyId, String b2BucketName) {
        this.name = name;
        this.driver = driver;
        this.b2AppKey = b2ApiKey;
        this.b2AppKeyId = b2ApiKeyId;
        this.b2BucketName = b2BucketName;
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

    public String getB2AppKey() {
        return b2AppKey;
    }

    public void setB2AppKey(String b2AppKey) {
        this.b2AppKey = b2AppKey;
    }

    public String getB2AppKeyId() {
        return b2AppKeyId;
    }

    public void setB2AppKeyId(String b2AppKeyId) {
        this.b2AppKeyId = b2AppKeyId;
    }

    public String getB2BucketName() {
        return b2BucketName;
    }

    public void setB2BucketName(String b2BucketName) {
        this.b2BucketName = b2BucketName;
    }
}
