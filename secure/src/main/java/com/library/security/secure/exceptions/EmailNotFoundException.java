package com.library.security.secure.exceptions;

import com.library.security.secure.utlities.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException(String email) {
        super(String.format(Constants.EMAIL_NOT_FOUND_EXCEPTION,email));
    }
}
