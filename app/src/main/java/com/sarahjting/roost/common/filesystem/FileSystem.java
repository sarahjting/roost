package com.sarahjting.roost.common.filesystem;

public interface FileSystem {
    void upload(String path, byte[] blob, FileSystemFileMetadata fileMetadata);
    void rename(String srcPath, String dstPath);
    void delete(String path);
}
