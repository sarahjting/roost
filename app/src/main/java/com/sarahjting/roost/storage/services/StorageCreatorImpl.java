package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDto;
import com.sarahjting.roost.storage.StorageRepository;
import com.sarahjting.roost.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StorageCreatorImpl implements StorageCreator {
    @Autowired
    StorageRepository storageRepository;

    @Override
    public Storage execute(User user, StorageDto storageDto) {
        Storage newStorage = new Storage();
        newStorage.setUser(user);
        newStorage.setName(storageDto.getName());
        newStorage.setDriver(storageDto.getDriver());

        // todo: calculate the endpoint if the driver is AWS
        newStorage.setEndpoint(storageDto.getEndpoint());

        // this needs to be encrypted
        newStorage.setAccessKey(storageDto.getAccessKey());
        newStorage.setSecretKey(storageDto.getSecretKey());
        newStorage.setBucketName(storageDto.getBucketName());
        newStorage.setCreatedAt(LocalDateTime.now());

//        this isn't needed anymore since columns got pulled out from metadata, might be needed for custom drivers later
//        Map<String, Object> storageMetadata = new HashMap();
//        storageMetadata.put("app_id", storageDto.getAccessKey());
//        storageMetadata.put("app_key", storageDto.getSecretKey());
//        storageMetadata.put("bucket_name", storageDto.getBucketName());

        return storageRepository.save(newStorage);
    }
}
