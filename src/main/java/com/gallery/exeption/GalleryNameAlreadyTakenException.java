package com.gallery.exeption;

public class GalleryNameAlreadyTakenException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Choosen gallery name is already taken." +
            "Please use another name";

    public GalleryNameAlreadyTakenException(){
        super(EXCEPTION_MESSAGE);
    }
}
