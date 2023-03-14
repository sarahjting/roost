package com.sarahjting.roost.common.image;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ImageTransformerRequest {
    Logger logger = LoggerFactory.getLogger(ImageTransformerRequest.class);
    Map<String, String[]> params = new HashMap<>();
    String fileName;
    String fileExtension;

    public ImageTransformerRequest(String routeParamString) {
        String[] pathParts = routeParamString.split("/");

        // process the last part
        String rawFileName = pathParts[pathParts.length - 1];
        fileExtension = FilenameUtils.getExtension(rawFileName);
        fileName = FilenameUtils.removeExtension(rawFileName);
        params.put("ext", new String[]{fileExtension});

        // process the middle parts
        String[] transformerParts = pathParts.length > 1 ? Arrays.copyOfRange(pathParts, 0, pathParts.length - 1) : new String[0];
        Arrays.stream(transformerParts).forEach(param -> {
            String[] paramParts = param.split("_");
            params.put(paramParts[0], paramParts.length > 1 ? Arrays.copyOfRange(paramParts, 1, paramParts.length) : new String[0]);
        });
    }

    public String[] get(String key) {
        return params.get(key);
    }

    public String getFirst(String key) {
        String[] parts = params.get(key);
        return parts != null && parts.length > 0 ? parts[0] : null;
    }

    public int getFirstInt(String key) throws NumberFormatException {
        return Integer.valueOf(getFirst(key));
    }

    public boolean has(String key) {
        return params.containsKey(key);
    }

    public boolean hasInt(String key) {
        try {
            getFirstInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
