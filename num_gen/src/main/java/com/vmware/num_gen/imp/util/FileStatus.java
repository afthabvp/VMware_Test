package com.vmware.num_gen.imp.util;

public enum FileStatus {

    SUCCESS ("SUCCESS"),
    ERROR ("ERROR"),
    IN_PROGRESS ("IN_PROGRESS");

    private String value;

    FileStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
