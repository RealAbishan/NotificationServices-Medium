package com.eliteprogramming.noticationservice.dto;

import java.io.Serializable;

public class EmailAttachmentDto implements Serializable {
    private String encodeFile;
    private String fileName;
    private String fileType;
    private String disposition;

    public String getEncodeFile() {
        return encodeFile;
    }

    public void setEncodeFile(String encodeFile) {
        this.encodeFile = encodeFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    @Override
    public String toString() {
        return "EmailAttachmentDto{" +
                "encodeFile='" + encodeFile + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", disposition='" + disposition + '\'' +
                '}';
    }
}
