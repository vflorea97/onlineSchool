package ro.mycode.onlineSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.onlineSchool.modele.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b order by b.bookName asc ")
    Optional<List<Book>> getBookByBookNameAsc();

    @Query("select b from Book b order by b.bookName desc")
    Optional<List<Book>> getBookByBookNameDesc();

    Optional<Book> findByBookName(String bookName);

    @Transactional
    Optional<Book> removeByBookName(String bookName);
}