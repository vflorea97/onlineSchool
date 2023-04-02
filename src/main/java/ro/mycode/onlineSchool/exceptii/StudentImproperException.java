package ro.mycode.onlineSchool.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentImproperException extends RuntimeException{

    public StudentImproperException() {
        super("Niciun student nu corespunde cu criteriile alese!!");
    }
}
