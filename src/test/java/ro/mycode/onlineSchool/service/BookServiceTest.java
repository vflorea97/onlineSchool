package ro.mycode.onlineSchool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.mycode.onlineSchool.comparatori.ComparatorBookNumeAsc;
import ro.mycode.onlineSchool.comparatori.ComparatorBookNumeDesc;
import ro.mycode.onlineSchool.exceptii.book.BookDBEmptyException;
import ro.mycode.onlineSchool.exceptii.book.BookFoundException;
import ro.mycode.onlineSchool.exceptii.book.BookNotFoundException;
import ro.mycode.onlineSchool.modele.Book;
import ro.mycode.onlineSchool.repository.BookRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Captor
    ArgumentCaptor<Book> bookArgumentCaptor;

    @Captor
    ArgumentCaptor<String> bookField1;

    @Test
    void getAllBooks() {
        Book book = Book.builder().bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        Book book2 = Book.builder().bookName("#chicagoGirl: The Social Network Takes on a Dictator").creationDate(LocalDate.of(2001, 3,24)).build();
        Book book3 = Book.builder().bookName("Death Note 2: The Last Name").creationDate(LocalDate.of(2000, 12,21)).build();
        Book book4 = Book.builder().bookName("3 Extremes (Three... Extremes) (Saam gaang yi)").creationDate(LocalDate.of(2002, 7,14)).build();
        Book book5 = Book.builder().bookName("Kites").creationDate(LocalDate.of(2010, 4,19)).build();
        Book book6 = Book.builder().bookName("Civic Duty").creationDate(LocalDate.of(2020, 3,8)).build();

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        doReturn(books).when(bookRepository).findAll();

        assertEquals(6, bookService.getAllBooks().size());
    }
    @Test
    void getAllBooksException() {
        List<Book> books = new ArrayList<>();
        doReturn(books).when(bookRepository).findAll();
        assertThrows(BookDBEmptyException.class, () ->{
           bookService.getAllBooks();
        });
    }

    @Test
    void addBook() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();

        doReturn(Optional.empty()).when(bookRepository).findByBookName("Paradise Lost: The Child Murders at Robin Hood Hills");

        bookService.addBook(book);

        verify(bookRepository, times(1)).saveAndFlush(bookArgumentCaptor.capture());

        assertEquals(bookArgumentCaptor.getValue(), book);
    }
    @Test
    void addBookException() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        doReturn(Optional.of(book)).when(bookRepository).findByBookName("Paradise Lost: The Child Murders at Robin Hood Hills");
        assertThrows(BookFoundException.class, () ->{
           bookService.addBook(book);
        });

    }


    @Test
    void removeBook() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();

        doReturn(Optional.of(book)).when(bookRepository).findByBookName("Paradise Lost: The Child Murders at Robin Hood Hills");

        bookService.removeBook("Paradise Lost: The Child Murders at Robin Hood Hills");

        verify(bookRepository, times(1)).removeByBookName(bookField1.capture());

        assertEquals("Paradise Lost: The Child Murders at Robin Hood Hills", bookField1.getValue());
    }
    @Test
    void removeBookException() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();

        doReturn(Optional.empty()).when(bookRepository).findByBookName("Paradise Lost: The Child Murders at Robin Hood Hills");

        assertThrows(BookNotFoundException.class, () ->{
           bookService.removeBook("Paradise Lost: The Child Murders at Robin Hood Hills");
        });
    }

    @Test
    void getBookByBookNameAsc() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        Book book2 = Book.builder().id(2L).bookName("#chicagoGirl: The Social Network Takes on a Dictator").creationDate(LocalDate.of(2001, 3,24)).build();
        Book book3 = Book.builder().id(3L).bookName("Death Note 2: The Last Name").creationDate(LocalDate.of(2000, 12,21)).build();
        Book book4 = Book.builder().id(4L).bookName("3 Extremes (Three... Extremes) (Saam gaang yi)").creationDate(LocalDate.of(2002, 7,14)).build();
        Book book5 = Book.builder().id(5L).bookName("Kites").creationDate(LocalDate.of(2010, 4,19)).build();
        Book book6 = Book.builder().id(6L).bookName("Civic Duty").creationDate(LocalDate.of(2020, 3,8)).build();

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        Collections.sort(books, new ComparatorBookNumeAsc());

        doReturn(Optional.of(books)).when(bookRepository).getBookByBookNameAsc();
        bookService.getBookByBookNameAsc();

        assertEquals(book2, books.get(0));
    }
    @Test
    void getBookByBookNameAscException() {
        List<Book> books = new ArrayList<>();
        doReturn(Optional.of(books)).when(bookRepository).getBookByBookNameAsc();
        assertThrows(BookDBEmptyException.class, () ->{
           bookService.getBookByBookNameAsc();
        });
    }

    @Test
    void getBookByBookNameDesc() {
        Book book = Book.builder().id(1L).bookName("Paradise Lost: The Child Murders at Robin Hood Hills").creationDate(LocalDate.of(2000, 3,13)).build();
        Book book2 = Book.builder().id(2L).bookName("#chicagoGirl: The Social Network Takes on a Dictator").creationDate(LocalDate.of(2001, 3,24)).build();
        Book book3 = Book.builder().id(3L).bookName("Death Note 2: The Last Name").creationDate(LocalDate.of(2000, 12,21)).build();
        Book book4 = Book.builder().id(4L).bookName("3 Extremes (Three... Extremes) (Saam gaang yi)").creationDate(LocalDate.of(2002, 7,14)).build();
        Book book5 = Book.builder().id(5L).bookName("Kites").creationDate(LocalDate.of(2010, 4,19)).build();
        Book book6 = Book.builder().id(6L).bookName("Civic Duty").creationDate(LocalDate.of(2020, 3,8)).build();

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        Collections.sort(books, new ComparatorBookNumeDesc());

        doReturn(Optional.of(books)).when(bookRepository).getBookByBookNameDesc();
        bookService.getBookByBookNameDesc();

        assertEquals(book, books.get(0));
    }
    @Test
    void getBookByBookNameDescException() {
        List<Book> books = new ArrayList<>();
        doReturn(Optional.of(books)).when(bookRepository).getBookByBookNameDesc();
        assertThrows(BookDBEmptyException.class, () ->{
            bookService.getBookByBookNameDesc();
        });
    }
}