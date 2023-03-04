package com.sarahjting.roost.storage.services;

import com.sarahjting.roost.common.filesystem.FileSystem;
import com.sarahjting.roost.storage.Storage;

public interface StorageFileSystemFactory {
    FileSystem getClient(Storage storage);
}
