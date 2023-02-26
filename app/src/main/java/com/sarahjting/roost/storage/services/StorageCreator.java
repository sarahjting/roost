package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.StorageDto;
import com.sarahjting.roost.user.User;

public interface StorageCreator {
    Storage execute(User user, StorageDto storageDto);
}
