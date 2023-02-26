package com.sarahjting.roost.storage.projections;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;

import java.util.UUID;

public class StorageBasicProjection {
    UUID id;
    String name;
    StorageDriver driver;

    public StorageBasicProjection(Storage storage) {
        this(storage.getId(), storage.getName(), storage.getDriver());
    }

    public StorageBasicProjection(UUID id, String name, StorageDriver driver) {
        this.id = id;
        this.name = name;
        this.driver = driver;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
