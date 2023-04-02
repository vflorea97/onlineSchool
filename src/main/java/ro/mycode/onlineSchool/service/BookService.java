package ro.mycode.onlineSchool.service;

import org.springframework.stereotype.Service;
import ro.mycode.onlineSchool.exceptii.book.BookDBEmptyException;
import ro.mycode.onlineSchool.exceptii.book.BookFoundException;
import ro.mycode.onlineSchool.exceptii.book.BookNotFoundException;
import ro.mycode.onlineSchool.modele.Book;
import ro.mycode.onlineSchool.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() throws BookDBEmptyException {
        List<Book> books = bookRepository.findAll();
        if (books.size() > 0) {
            return books;
        }else {
            throw new BookDBEmptyException();
        }
    }

    @Transactional
    public void addBook(Book book) throws BookFoundException {
        Optional<Book> book1 = bookRepository.findByBookName(book.getBookName());
        if(book1.isEmpty()){
            bookRepository.saveAndFlush(book);
        }else{
            throw  new BookFoundException();
        }
    }

    @Transactional
    public void removeBook(String bookName) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findByBookName(bookName);
        if (book.isPresent()){
            bookRepository.removeByBookName(bookName);
        }else {
            throw new BookNotFoundException();
        }
    }

    public List<Book> getBookByBookNameAsc() throws BookDBEmptyException {
        List<Book> books = bookRepository.getBookByBookNameAsc().get();
        if (books.size() > 0){
            return books;
        }else {
            throw new BookDBEmptyException();
        }
    }

    public List<Book> getBookByBookNameDesc() throws BookDBEmptyException {
        List<Book> books = bookRepository.getBookByBookNameDesc().get();
        if (books.size() > 0){
            return books;
        }else {
            throw new BookDBEmptyException();
        }
    }




}
