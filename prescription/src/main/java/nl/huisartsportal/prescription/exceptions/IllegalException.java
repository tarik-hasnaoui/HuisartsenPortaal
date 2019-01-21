package nl.huisartsportal.prescription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class IllegalException extends RuntimeException {

    public IllegalException(String message) {
        super(message);
    }

    public IllegalException() {
        super();
    }
}
