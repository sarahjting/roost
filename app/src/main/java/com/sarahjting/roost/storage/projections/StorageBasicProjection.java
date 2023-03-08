package com.sarahjting.roost.storage.projections;

import com.sarahjting.roost.storage.StorageDriver;

import java.util.UUID;

public interface StorageBasicProjection {
    UUID getId();
    String getName();
    StorageDriver getDriver();
}
