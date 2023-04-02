package ro.mycode.onlineSchool.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.mycode.onlineSchool.comparatori.ComparatorStudentNumeDesc;
import ro.mycode.onlineSchool.exceptii.StudentDBEmptyException;
import ro.mycode.onlineSchool.exceptii.StudentFoundException;
import ro.mycode.onlineSchool.exceptii.StudentImproperException;
import ro.mycode.onlineSchool.exceptii.StudentNotFoundException;
import ro.mycode.onlineSchool.modele.Student;
import ro.mycode.onlineSchool.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Captor
    ArgumentCaptor<Student> studentArgumentCaptor;
    @Captor
    ArgumentCaptor<String> studentField1;

    @Test
    void gelAllStudents() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.dsadascom").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdsadasdasd.com").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdadsan@gamil.ro").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400das@gmail.com").build();

        List<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        doReturn(students).when(studentRepository).findAll();

        assertEquals(5,studentService.gelAllStudents().size());
    }

    @Test
    public  void getAllStudentsEmpty(){
        List<Student> students = new ArrayList<>();
        doReturn(students).when(studentRepository).findAll();
        assertThrows(StudentDBEmptyException.class, () -> {
            studentService.gelAllStudents();
        });
    }

    @Test
    void getStudentByEmail() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");

        assertEquals(Optional.of(student), studentService.getStudentByEmail("dsadas@dadasdsd.com"));
    }
    @Test
    public void getStudentByEmailException() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");
        assertThrows(StudentImproperException.class, () -> {
            studentService.getStudentByEmail("dsadas@dadasdsd.com");
        });
    }

    @Test
    public void addStudent(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();

        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");

        studentService.addStudent(student);

        verify(studentRepository,times(1)).saveAndFlush(studentArgumentCaptor.capture());

        assertEquals(studentArgumentCaptor.getValue(),student);
    }
    @Test
    public void addStudentExceptie(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");

        assertThrows(StudentFoundException.class, () ->{
            studentService.addStudent(student);
        });
    }

    @Test
    public void removeStudent(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");

        studentService.removeStudent("dsadas@dadasdsd.com");

        verify(studentRepository,times(1)).removeStudentByEmail(studentField1.capture());

        assertEquals("dsadas@dadasdsd.com",studentField1.getValue());
    }
    @Test
    public void removeStudentExceptie() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("dsadas@dadasdsd.com");

        assertThrows(StudentNotFoundException.class, () ->{
            studentService.removeStudent("dsadas@dadasdsd.com");
        });
    }

    @Test
    public void ordineNumeAsc() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.dsadascom").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdsadasdasd.com").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdadsan@gamil.ro").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400das@gmail.com").build();

        List<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);


        Collections.sort(students);
        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameAsc();
        studentService.ordineNumeAsc();

        assertEquals(student3, students.get(0));
    }
    @Test
    public void ordineNumeAscExceptie(){
        List<Student> students = new ArrayList<>();

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameAsc();

        assertThrows(StudentDBEmptyException.class, () ->{
            studentService.ordineNumeAsc();
        });
    }

    @Test
    public void  ordineNumeDesc() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dadasdsd.com").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.dsadascom").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdsadasdasd.com").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdadsan@gamil.ro").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400das@gmail.com").build();

        List<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);


        Collections.sort(students, new ComparatorStudentNumeDesc());

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameDesc();
        studentService.ordineNumeDesc();

        assertEquals(student4, students.get(0));
    }
    @Test
    public void ordineNumeDescExceptie(){
        List<Student> students = new ArrayList<>();

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameDesc();

        assertThrows(StudentDBEmptyException.class, () ->{
            studentService.ordineNumeDesc();
        });
    }
}