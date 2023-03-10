package com.sarahjting.roost.upload.services;

import com.sarahjting.roost.common.util.StringFormatter;
import com.sarahjting.roost.storage.Storage;
import com.sarahjting.roost.upload.Upload;
import com.sarahjting.roost.upload.UploadStatus;
import com.sarahjting.roost.upload.UploadType;
import com.sarahjting.roost.user.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UploadFactoryImpl implements UploadFactory {
    Logger logger = LoggerFactory.getLogger(UploadFactoryImpl.class);

    @Override
    public Upload getUpload(User user, Storage storage, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        Tika tika = new Tika();
        MimeType mimeType = MimeType.valueOf(tika.detect(bytes));
        String originalExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String originalFileName = FilenameUtils.removeExtension(file.getOriginalFilename());

        Upload upload = new Upload();
        upload.setUser(user);
        upload.setStatus(UploadStatus.CREATED);
        upload.setFileName(StringFormatter.fileNameToSlug(originalFileName));
        upload.setOriginalFileName(originalFileName);
        upload.setOriginalFileExtension(originalExtension);
        upload.setFileSize(file.getSize());
        upload.setStorage(storage);
        upload.setMimeType(mimeType.toString());

        String mimeTypeGroup = mimeType.toString().split("/")[0];
        if (mimeTypeGroup.equals("image")) {
            upload.setType(UploadType.IMAGE);

            // cool, looks like i don't need imagick for this
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            if (bufferedImage != null) { // this happens if the file read fails
                upload.setImageHeight(Long.valueOf(bufferedImage.getHeight()));
                upload.setImageWidth(Long.valueOf(bufferedImage.getWidth()));
            }
        } else if (mimeTypeGroup.equals("text")) {
            upload.setType(UploadType.TEXT);
        } else {
            upload.setType(UploadType.FILE);
        }

        upload.setCreatedAt(LocalDateTime.now());
        return upload;
    }
}
