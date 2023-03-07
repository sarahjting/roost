package com.sarahjting.roost.upload.listeners;

import com.sarahjting.roost.upload.UploadLog;
import com.sarahjting.roost.upload.UploadLogRepository;
import com.sarahjting.roost.upload.events.UploadFileUploaded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UploadListener {
    Logger logger = LoggerFactory.getLogger(UploadListener.class);

    @Autowired
    UploadLogRepository uploadLogRepository;

    // these run synchronously
    @EventListener
    void createUploadLog(UploadFileUploaded event) {
        UploadLog log = new UploadLog(
            event.getUser(),
            event.getUpload(),
            event.getUploadResponse().getVersionId()
        );
        uploadLogRepository.save(log);
    }
}
