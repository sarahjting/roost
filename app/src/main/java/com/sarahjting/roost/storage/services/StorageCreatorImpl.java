package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDriver;
import com.sarahjting.roost.storage.StorageDto;
import com.sarahjting.roost.storage.StorageRepository;
import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StorageCreatorImpl implements StorageCreator {
    @Autowired
    StorageRepository storageRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public Storage execute(User user, StorageDto storageDto) {
        Storage newStorage = new Storage();
        newStorage.setUser(user);
        newStorage.setName(storageDto.getName());
        newStorage.setDriver(storageDto.getDriver());

        if (storageDto.getDriver() == StorageDriver.S3) {
            newStorage.setEndpoint("com.amazonaws." + storageDto.getRegion() + ".s3");
        } else {
            newStorage.setEndpoint(storageDto.getEndpoint());
        }

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

        newStorage = storageRepository.save(newStorage);

        if (user.getDefaultStorage() == null) {
            user.setDefaultStorage(newStorage);
            userService.save(user);
        }

        return newStorage;
    }
}
