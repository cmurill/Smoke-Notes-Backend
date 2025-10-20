package dev.cmurillo.SmokeNotesBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingCigarException extends RuntimeException {
    public ExistingCigarException(String message) {
        super(message);
    }
}
