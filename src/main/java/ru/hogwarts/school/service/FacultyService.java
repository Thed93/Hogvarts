package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {

    Optional<Faculty> findFacultyById(long facultyId);

    Collection<Faculty> getAllFaculties();

    Faculty createFaculty (Faculty faculty);

    Faculty findFaculty (long id);

    Faculty editFaculty (Faculty faculty);

    void removeFaculty (long id);

    Collection<Faculty> findByColorOrName (String colorOrName);

    Collection<Student> getStudentsbyFaculty(String name);
}
