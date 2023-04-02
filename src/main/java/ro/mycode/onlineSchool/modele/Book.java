package ro.mycode.onlineSchool.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity(name="Book")
@Table(name="book")
public class Book implements Comparable<Book>{


    @Id
    @SequenceGenerator(name = "books_sequence", sequenceName = "books_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_sequence")
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    private LocalDate creationDate;


    @ManyToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "user_id_fk")

    )
    @JsonBackReference
    private Student student;

    @Override
    public int compareTo(Book o) {
        if (this.id > o.id){
            return 1;
        }
        else if(this.id < o.id){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o){
        Book book = (Book) o;
        return this.id.equals(book.id);
    }


}
