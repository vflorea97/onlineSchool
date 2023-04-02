package ro.mycode.onlineSchool.exceptii.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookDBEmptyException extends RuntimeException{

    public BookDBEmptyException() {
        super("Baza de date este goala!!");
    }
}
