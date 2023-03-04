package com.sarahjting.roost.common.filesystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileSystemCredentials {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    public FileSystemCredentials(String endpoint, String accessKey, String secretKey, String bucketName) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }
}
