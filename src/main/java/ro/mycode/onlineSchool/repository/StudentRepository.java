package ro.mycode.onlineSchool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.onlineSchool.modele.Student;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s order by s.nume asc")
    Optional<List<Student>> getStudentsOrderByNameAsc();

    @Query("select s from Student s order by s.nume desc ")
    Optional<List<Student>> getStudentsOrderByNameDesc();

    Optional<Student> findStudentByEmail(String email);

    @Transactional
    Optional<Student> removeStudentByEmail(String email);
}
