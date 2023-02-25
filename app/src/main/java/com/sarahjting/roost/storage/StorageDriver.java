package com.sarahjting.roost.storage;

public enum StorageDriver {
    B2("B2");

    private final String label;

    private StorageDriver(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
