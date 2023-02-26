package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDto;
import com.sarahjting.roost.storage.StorageRepository;
import com.sarahjting.roost.user.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StorageCreatorImpl implements StorageCreator {
    StorageRepository storageRepository;

    @Override
    public Storage execute(User user, StorageDto storageDto) {
        Storage newStorage = new Storage();
        newStorage.setUser(user);
        newStorage.setName(storageDto.getName());
        newStorage.setDriver(storageDto.getDriver());

        Map<String, Object> storageMetadata = new HashMap();
        storageMetadata.put("app_id", storageDto.getB2AppKeyId());
        storageMetadata.put("app_key", storageDto.getB2AppKey());
        storageMetadata.put("bucket_name", storageDto.getB2BucketName());

        newStorage.setMetadata(storageMetadata);
        return storageRepository.save(newStorage);
    }
}
