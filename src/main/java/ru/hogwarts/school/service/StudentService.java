package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Lust5Students;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Collection<Student> getAllStudents();

    Student createStudent (Student student);

    Student findStudent(long id);

    Student editStudent (Student student);

    void removeStudent (long id);

    Collection <Student> findByAgeBetween (int min, int max);

    Faculty getFacultyByName(String name);

    Integer getNumberOfStudents();

    Integer getAverageAgeOfStudents();

    List<Lust5Students> getLust5Students();

    List<String> getStudentsStartWithA();

    double getAvgAge();

    Integer sumImpr();

    Integer sumIm();
}
