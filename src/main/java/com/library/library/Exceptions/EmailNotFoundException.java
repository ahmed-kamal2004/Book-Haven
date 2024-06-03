package com.library.library.Exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(){
        super("Email Not Found");
    }
}
