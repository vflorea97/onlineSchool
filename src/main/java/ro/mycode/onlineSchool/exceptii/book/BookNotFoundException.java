package ro.mycode.onlineSchool.exceptii.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("Aceasta carte nu exista in baza de date!!");
    }
}
