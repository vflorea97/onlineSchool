package ro.mycode.onlineSchool.service;

import org.springframework.stereotype.Service;
import ro.mycode.onlineSchool.exceptii.StudentDBEmptyException;
import ro.mycode.onlineSchool.exceptii.StudentFoundException;
import ro.mycode.onlineSchool.exceptii.StudentImproperException;
import ro.mycode.onlineSchool.exceptii.StudentNotFoundException;
import ro.mycode.onlineSchool.modele.Student;
import ro.mycode.onlineSchool.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> gelAllStudents() throws StudentDBEmptyException {
        List<Student> students = studentRepository.findAll();
        if (students.size() > 0) {
            return students;
        }
        throw new StudentDBEmptyException();
    }

    public Optional<Student> getStudentByEmail(String email) throws StudentImproperException {
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            return student;
        }
        throw new StudentImproperException();
    }

    @Transactional
    public void addStudent(Student student) throws StudentFoundException {
        Optional<Student> student1 = studentRepository.findStudentByEmail(student.getEmail());
        if (student1.isEmpty()){
            studentRepository.saveAndFlush(student);
        }else{
            throw new StudentFoundException();
        }
    }

    @Transactional
    public void removeStudent(String email) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            studentRepository.removeStudentByEmail(email);
        }else{
            throw new StudentNotFoundException();
        }
    }

    public List<Student> ordineNumeAsc() throws StudentDBEmptyException {
        List<Student> students = studentRepository.getStudentsOrderByNameAsc().get();
        if (students.size() > 0){
            return students;
        }else{
            throw new StudentDBEmptyException();
        }
    }

    public List<Student> ordineNumeDesc() throws StudentDBEmptyException {
        List<Student> students = studentRepository.getStudentsOrderByNameDesc().get();
        if (students.size() > 0){
            return students;
        }else{
            throw new StudentDBEmptyException();
        }
    }
}
