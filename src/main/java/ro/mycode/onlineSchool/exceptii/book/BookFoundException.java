package ro.mycode.onlineSchool.exceptii.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookFoundException extends RuntimeException{

    public BookFoundException() {
        super("Aceasta carte exista deja in baza de date!!");
    }
}
