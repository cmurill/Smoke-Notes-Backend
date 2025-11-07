package dev.cmurillo.SmokeNotesBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingUserCigarException extends RuntimeException {
    public ExistingUserCigarException(String message) {
        super(message);
    }
}
