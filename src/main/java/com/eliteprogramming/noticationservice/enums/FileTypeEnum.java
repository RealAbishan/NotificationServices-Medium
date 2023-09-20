package com.eliteprogramming.noticationservice.enums;

public enum FileTypeEnum {

    CSV(".csv"),
    Excel(".xlsx"),
    Word(".docx"),
    ZIP(".zip");

    private final String value;

    FileTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getType() {
        return value;
    }
}
