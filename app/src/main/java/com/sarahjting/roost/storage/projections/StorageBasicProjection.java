package com.sarahjting.roost.storage.projections;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StorageBasicProjection {
    UUID id;
    String name;
    StorageDriver driver;

    public StorageBasicProjection(UUID id, String name, StorageDriver driver) {
        this.id = id;
        this.name = name;
        this.driver = driver;
    }

    public static StorageBasicProjection fromStorage(Storage storage) {
        return new StorageBasicProjection(storage.getId(), storage.getName(), storage.getDriver());
    }
}
