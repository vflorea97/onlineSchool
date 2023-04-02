package ro.mycode.onlineSchool.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.mycode.onlineSchool.OnlineSchoolApplication;
import ro.mycode.onlineSchool.modele.Student;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApplication.class)
@Transactional
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach

    public void clean(){

        studentRepository.deleteAll();
    }

    @Test
    void getStudentsOrderByNameAsc() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.dsadascom").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdsadasdasd.com").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdadsan@gamil.ro").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400das@gmail.com").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);


        List<Student> students =studentRepository.getStudentsOrderByNameAsc().get();

        assertEquals(student3,students.get(0));
    }

    @Test
    void getStudentsOrderByNameDesc() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.dsadascom").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdsadasdasd.com").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdadsan@gamil.ro").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400das@gmail.com").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);

        List<Student> students = studentRepository.getStudentsOrderByNameDesc().get();

        assertEquals(student4,students.get(0));

    }

    @Test
    void findStudentByEmail() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("testtest@gmail.com").build();

        studentRepository.saveAndFlush(student);

        Student student2 = studentRepository.findStudentByEmail("testtest@gmail.com").get();

        assertEquals(student, student2);
    }

    @Test
    void removeStudentByEmail() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("testtest@gmail.com").build();

        studentRepository.saveAndFlush(student);

        studentRepository.removeStudentByEmail("testtest@gmail.com");
        List<Student> students = studentRepository.getStudentsOrderByNameAsc().get();
        assertEquals(false,students.contains(student));
    }
}