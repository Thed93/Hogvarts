package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Lust5Students;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);

    Student findStudentByName (String name);

    @Query(value = "select count(*) FROM student;", nativeQuery = true)
    Integer getNumberOfStudents();

    @Query(value = "SELECT AVG(age) FROM student;", nativeQuery = true)
    Integer getAverageAgeOfStudents();

    @Query(value = "SELECT name from student order by id DESC LIMIT 5;", nativeQuery = true)
    List<Lust5Students> getLust5Students();

}
