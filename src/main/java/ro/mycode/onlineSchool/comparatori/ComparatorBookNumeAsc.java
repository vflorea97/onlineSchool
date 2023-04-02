package ro.mycode.onlineSchool.comparatori;

import ro.mycode.onlineSchool.modele.Book;

import java.util.Comparator;

public class ComparatorBookNumeAsc implements Comparator<Book> {
    @Override
    public int compare(Book book, Book t1) {
        return Integer.compare(book.getBookName().compareTo(t1.getBookName()), 0);
    }
}
