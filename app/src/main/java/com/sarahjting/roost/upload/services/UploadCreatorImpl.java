package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.common.filesystem.FileSystem;
import com.sarahjting.roost.common.filesystem.FileSystemFileMetadata;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.services.StorageFileSystemFactory;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.upload.UploadRepository;
import com.sarahjting.roost.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadCreatorImpl implements UploadCreator {
    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    StorageFileSystemFactory fileSystemFactory;

    @Autowired
    UploadFactory uploadFactory;

    @Override
    @Transactional
    public Upload execute(User user, Storage storage, MultipartFile file) throws IOException {
        Upload upload = uploadFactory.getUpload(user, storage, file);

        FileSystem fileSystem = fileSystemFactory.getClient(storage);
        fileSystem.upload(
            upload.getFilePath(),
            file.getBytes(),
            new FileSystemFileMetadata(MimeType.valueOf(upload.getMimeType()))
        );

        return uploadRepository.save(upload);
    }
}
