package ro.mycode.onlineSchool.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.mycode.onlineSchool.OnlineSchoolApplication;
import ro.mycode.onlineSchool.modele.Book;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApplication.class)
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void clean(){
        bookRepository.deleteAll();;
    }

    @Test
    void getBookByBookNameAsc() {

        Book book = Book.builder().bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        Book book2 = Book.builder().bookName("#chicagoGirl: The Social Network Takes on a Dictator").creationDate(LocalDate.of(2001, 3,24)).build();
        Book book3 = Book.builder().bookName("Death Note 2: The Last Name").creationDate(LocalDate.of(2000, 12,21)).build();
        Book book4 = Book.builder().bookName("3 Extremes (Three... Extremes) (Saam gaang yi)").creationDate(LocalDate.of(2002, 7,14)).build();
        Book book5 = Book.builder().bookName("Kites").creationDate(LocalDate.of(2010, 4,19)).build();
        Book book6 = Book.builder().bookName("Civic Duty").creationDate(LocalDate.of(2020, 3,8)).build();

        bookRepository.saveAndFlush(book);
        bookRepository.saveAndFlush(book2);
        bookRepository.saveAndFlush(book3);
        bookRepository.saveAndFlush(book4);
        bookRepository.saveAndFlush(book5);
        bookRepository.saveAndFlush(book6);

        List<Book> bookList = bookRepository.getBookByBookNameAsc().get();

        assertEquals(book2, bookList.get(0));

    }

    @Test
    void getBookByBookNameDesc() {
        Book book = Book.builder().bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        Book book2 = Book.builder().bookName("#chicagoGirl: The Social Network Takes on a Dictator").creationDate(LocalDate.of(2001, 3,24)).build();
        Book book3 = Book.builder().bookName("Death Note 2: The Last Name").creationDate(LocalDate.of(2000, 12,21)).build();
        Book book4 = Book.builder().bookName("3 Extremes (Three... Extremes) (Saam gaang yi)").creationDate(LocalDate.of(2002, 7,14)).build();
        Book book5 = Book.builder().bookName("Kites").creationDate(LocalDate.of(2010, 4,19)).build();
        Book book6 = Book.builder().bookName("Civic Duty").creationDate(LocalDate.of(2020, 3,8)).build();

        bookRepository.saveAndFlush(book);
        bookRepository.saveAndFlush(book2);
        bookRepository.saveAndFlush(book3);
        bookRepository.saveAndFlush(book4);
        bookRepository.saveAndFlush(book5);
        bookRepository.saveAndFlush(book6);

        List<Book> bookList = bookRepository.getBookByBookNameDesc().get();

        assertEquals(book, bookList.get(0));
    }

}