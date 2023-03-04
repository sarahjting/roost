package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFactory {
    Upload getUpload(User user, Storage storage, MultipartFile file) throws IOException;
}
