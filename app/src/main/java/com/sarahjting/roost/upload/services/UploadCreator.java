package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface UploadCreator {
    Upload execute(User user, Storage storage, MultipartFile file);
}
