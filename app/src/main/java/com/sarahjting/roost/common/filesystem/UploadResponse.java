package com.sarahjting.roost.common.filesystem;

import lombok.Getter;
import java.util.Map;

@Getter
public class UploadResponse {
    private String versionId;
    private String eTag;
    private String contentMd5;
    private Map metadata;

    public UploadResponse(String versionId, String eTag, String contentMd5, Map metadata) {
        this.versionId = versionId;
        this.eTag = eTag;
        this.contentMd5 = contentMd5;
        this.metadata = metadata;
    }
}
