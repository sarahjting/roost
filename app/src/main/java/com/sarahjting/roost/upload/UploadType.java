package com.sarahjting.roost.upload;

public enum UploadType {
    IMAGE("IMAGE"),
    HTML("HTML"),
    TEXT("TEXT"),
    FILE("FILE");

    private final String label;

    private UploadType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
