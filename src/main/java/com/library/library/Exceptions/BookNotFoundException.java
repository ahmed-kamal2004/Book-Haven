package com.library.library.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book Not Found Exception");
    }
}
