package com.sarahjting.roost.upload;

public enum UploadStatus {
    CREATED("CREATED"),
    UPLOADED("UPLOADED"),
    DELETED("DELETED");

    private final String label;

    private UploadStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
