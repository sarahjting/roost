package com.sarahjting.roost.upload.events;

import com.sarahjting.roost.common.filesystem.UploadResponse;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.user.User;
import lombok.Getter;

@Getter
public class UploadFileUploaded {
    User user;
    Upload upload;
    UploadResponse uploadResponse;

    public UploadFileUploaded(User user, Upload upload, UploadResponse uploadResponse) {
        this.user = user;
        this.upload = upload;
        this.uploadResponse = uploadResponse;
    }
}
