package ro.mycode.onlineSchool.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentDBEmptyException extends RuntimeException{

    public StudentDBEmptyException() {
        super("Baza de date este goala!!");
    }
}
