package dev.cmurillo.SmokeNotesBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CigarNotFoundException extends RuntimeException {
    public CigarNotFoundException(String message) {
        super(message);
    }
}
