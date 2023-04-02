package ro.mycode.onlineSchool.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.mycode.onlineSchool.modele.Student;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentFoundException extends RuntimeException{

    public StudentFoundException() {
        super("Studentul deja exista in baza de date!!");
    }
}
