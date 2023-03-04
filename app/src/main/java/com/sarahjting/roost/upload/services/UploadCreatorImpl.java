package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.upload.UploadRepository;
import com.sarahjting.roost.upload.UploadType;
import com.sarahjting.roost.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class UploadCreatorImpl implements UploadCreator {
    @Autowired
    UploadRepository uploadRepository;

    @Override
    public Upload execute(User user, Storage storage, MultipartFile file) {
        // TODO: upload file

        Upload newUpload = new Upload();
        newUpload.setUser(user);
        newUpload.setFileName(file.getOriginalFilename());
        newUpload.setOriginalFileName(file.getOriginalFilename());
        newUpload.setFileSize(file.getSize());
        newUpload.setStorage(storage);

        // TODO: get extension and mime type
        newUpload.setMimeType("test");

        // TODO: figure out file type from format
        newUpload.setType(UploadType.FILE);

        // TODO: image processing
        newUpload.setImageHeight(null);
        newUpload.setImageWidth(null);

        newUpload.setCreatedAt(LocalDateTime.now());
        newUpload = uploadRepository.save(newUpload);

        return newUpload;
    }
}
