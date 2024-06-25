package com.library.library.Exceptions;

public class IsbnExistsException extends RuntimeException {

    public IsbnExistsException() {
        super("ISBN Exists");
    }
}
