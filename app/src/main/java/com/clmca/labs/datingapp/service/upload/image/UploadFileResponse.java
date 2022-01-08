package com.clmca.labs.datingapp.service.upload.image;

@lombok.Data
public class UploadFileResponse {
    private int status;
    private boolean success;
    private Data data;
}