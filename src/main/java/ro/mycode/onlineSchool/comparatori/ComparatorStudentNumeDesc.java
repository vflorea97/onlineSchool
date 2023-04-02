package ro.mycode.onlineSchool.comparatori;

import ro.mycode.onlineSchool.modele.Student;

import java.util.Comparator;

public class ComparatorStudentNumeDesc implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(0, s1.getNume().compareTo(s2.getNume()));
    }
}
