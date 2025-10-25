package dev.cmurillo.SmokeNotesBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserCigarNotFoundException extends RuntimeException {
    public UserCigarNotFoundException(String message) {
        super(message);
    }
}
