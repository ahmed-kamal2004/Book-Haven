package com.library.security.secure.exceptions;

import com.library.security.secure.utlities.Constants;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String email){
        super(String.format(Constants.EMAIL_ALREADY_EXIST_EXCEPTION,email));
    }
}
