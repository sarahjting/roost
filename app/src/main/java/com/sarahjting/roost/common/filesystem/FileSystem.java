package com.sarahjting.roost.common.filesystem;

public interface FileSystem {
    UploadResponse upload(String path, byte[] blob, FileSystemFileMetadata fileMetadata);
    void rename(String srcPath, String dstPath);
    void delete(String path);
}
