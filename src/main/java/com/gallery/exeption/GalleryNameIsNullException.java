package com.gallery.exeption;

public class GalleryNameIsNullException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Gallery name can't be empty";

    public GalleryNameIsNullException(){
        super(EXCEPTION_MESSAGE);
    }
}
