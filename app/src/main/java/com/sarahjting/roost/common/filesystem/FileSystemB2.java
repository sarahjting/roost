package com.sarahjting.roost.common.filesystem;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class FileSystemB2 implements FileSystem {

    private FileSystemS3 s3;
    private FileSystemCredentials credentials;

    public FileSystemB2(FileSystemCredentials credentials) {
        this.credentials = credentials;
        this.s3 = new FileSystemS3(this.credentials);
    }

    @Override
    public void upload(String path, byte[] blob, FileSystemFileMetadata fileMetadata) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(fileMetadata.getMimeType().toString());
        objectMetadata.setHeader("X-Bz-Content-Sha1", generateSha1FromBlob(blob));

        s3.upload(path, blob, objectMetadata);
    }

    private String generateSha1FromBlob(byte[] blob) {
        try {
            MessageDigest digest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
            byte[] hash = digest.digest(blob);
            String hashString = HexFormat.of().formatHex(hash);

            return hashString;
        } catch (NoSuchAlgorithmException e) {
            return null; // this case is actually acceptable; we just upload the file with an unverified SHA-1
        }
    }

    @Override
    public void rename(String srcPath, String dstPath) {
        s3.rename(srcPath, dstPath);
    }

    @Override
    public void delete(String path) {
        s3.delete(path);
    }
}
