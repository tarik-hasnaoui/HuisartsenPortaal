package nl.huisartsPortal.prescription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarik on 7-11-2018.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }

    public DataNotFoundException(){
        super();
    }
}
