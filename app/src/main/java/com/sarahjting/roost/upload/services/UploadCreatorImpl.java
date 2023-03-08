package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.common.filesystem.FileSystem;
import com.sarahjting.roost.common.filesystem.FileSystemFileMetadata;
import com.sarahjting.roost.common.filesystem.UploadResponse;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.storage.services.StorageFileSystemFactory;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.upload.UploadRepository;
import com.sarahjting.roost.upload.UploadStatus;
import com.sarahjting.roost.upload.events.UploadFileUploaded;
import com.sarahjting.roost.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UploadCreatorImpl implements UploadCreator {
    @Autowired
    ApplicationEventPublisher eventPublisher;

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

        Optional<Upload> existingUpload = uploadRepository.findOneByUserAndFileName(user, upload.getFileName());
        if (existingUpload.isPresent()) {
            setNextFileName(upload, upload.getOriginalFileName());
        }

        for (int i = 0; i < 3; i ++) {
            try {
                upload = uploadRepository.save(upload);
                break;
            } catch(DataIntegrityViolationException e) {
                setNextFileName(upload, upload.getOriginalFileName());
            }
        }

        FileSystem fileSystem = fileSystemFactory.getClient(storage);
        UploadResponse uploadResponse = fileSystem.upload(
            upload.getFilePath(),
            file.getBytes(),
            new FileSystemFileMetadata(MimeType.valueOf(upload.getMimeType()))
        );

        upload.setStatus(UploadStatus.UPLOADED);
        upload = uploadRepository.save(upload);

        eventPublisher.publishEvent(new UploadFileUploaded(user, upload, uploadResponse));

        return upload;
    }

    private Upload setNextFileName(Upload upload, String originalFileName) {
        String fileNameWithTimestamp = String.format("%s-%s", originalFileName, System.currentTimeMillis());
        upload.setFileName(fileNameWithTimestamp);
        return upload;
    }
}
