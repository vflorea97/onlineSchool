package ro.mycode.onlineSchool.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException() {
        super("Acest student nu exista in baza de date!!");
    }
}
