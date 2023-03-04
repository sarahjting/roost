package com.sarahjting.roost.common.filesystem;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MimeType;

@Getter
@Setter
public class FileSystemFileMetadata {
    private MimeType mimeType;

    public FileSystemFileMetadata(MimeType mimeType) {
        this.mimeType = mimeType;
    }
}
