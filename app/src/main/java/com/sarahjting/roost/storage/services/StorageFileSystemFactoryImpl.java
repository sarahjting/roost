package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.common.filesystem.FileSystem;
import com.sarahjting.roost.common.filesystem.FileSystemB2;
import com.sarahjting.roost.common.filesystem.FileSystemCredentials;
import com.sarahjting.roost.common.filesystem.FileSystemS3;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;
import org.springframework.stereotype.Service;

@Service
class StorageFileSystemFactoryImpl implements StorageFileSystemFactory {
    @Override
    public FileSystem getClient(Storage storage) {
        FileSystemCredentials credentials = new FileSystemCredentials(
                storage.getEndpoint(),
                storage.getAccessKey(),
                storage.getSecretKey(),
                storage.getBucketName()
        );
        if (storage.getDriver() == StorageDriver.B2) {
            return new FileSystemB2(credentials);
        } else {
            return new FileSystemS3(credentials);
        }
    }
}
