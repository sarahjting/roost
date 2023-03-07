package com.sarahjting.roost.common.filesystem;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class FileSystemS3 implements FileSystem {
    Logger logger = LoggerFactory.getLogger(FileSystemS3.class);

    private AmazonS3 s3;
    private FileSystemCredentials credentials;

    public FileSystemS3(FileSystemCredentials credentials) {
        this.credentials = credentials;

        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(credentials.getAccessKey(), credentials.getSecretKey())
        );

        // we might need a region here
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(credentials.getEndpoint(), null);

        this.s3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }

    @Override
    public UploadResponse upload(String path, byte[] blob, FileSystemFileMetadata fileMetadata) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(fileMetadata.getMimeType().toString());
        return upload(path, blob, objectMetadata);
    }

    public UploadResponse upload(String path, byte[] blob, ObjectMetadata objectMetadata) {
        PutObjectResult putObjectResult = s3.putObject(credentials.getBucketName(), path, new ByteArrayInputStream(blob), objectMetadata);
        return new UploadResponse(
            putObjectResult.getVersionId(),
            putObjectResult.getETag(),
            putObjectResult.getContentMd5(),
            putObjectResult.getMetadata().getRawMetadata()
        );
    }

    @Override
    public void rename(String srcPath, String dstPath) {
        s3.copyObject(credentials.getBucketName(), srcPath, dstPath, credentials.getBucketName());
        delete(srcPath);
    }

    @Override
    public void delete(String path) {
        s3.deleteObject(credentials.getBucketName(), path);
    }
}
