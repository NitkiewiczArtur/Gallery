package com.gallery.exeption;

public class NoPhotoUploadedException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "No photo was selected to upload.";

    public NoPhotoUploadedException(){
        super(EXCEPTION_MESSAGE);
    }
}
