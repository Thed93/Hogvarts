package ru.hogwarts.school.service.production;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceProduction implements FacultyService {
    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceProduction.class);
    public FacultyServiceProduction(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Optional<Faculty> findFacultyById(long facultyId) {
        logger.debug("Вызван метод 'findFacultyById'");
        return Optional.of(facultyRepository.findById(facultyId).orElseThrow());
    }
    public Collection<Faculty> getAllFaculties(){
        logger.debug("Вызван метод 'getAllFaculties'");
        return facultyRepository.findAll();
    }
    public Faculty createFaculty (Faculty faculty) {
        logger.debug("Вызван метод 'createFaculty'");
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty (long id) {
        logger.debug("Вызван метод 'findFaculty'");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty (Faculty faculty) {
        logger.debug("Вызван метод 'editFaculty'");
        return facultyRepository.save(faculty);
    }
    public void removeFaculty (long id) {
        logger.debug("Вызван метод 'removeFaculty'");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColorOrName (String colorOrName){
        logger.debug("Вызван метод 'findByColorOrName'");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }

    public Collection<Student> getStudentsbyFaculty(String name) {
        logger.debug("Вызван метод 'getStudentsbyFaculty'");
        return facultyRepository.findFacultyByName(name).getStudents();

    }

}