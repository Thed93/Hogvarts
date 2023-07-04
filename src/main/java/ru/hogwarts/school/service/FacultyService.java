package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Collection<Faculty> getAllFaculties(){
        return faculties.values();
    }
    private long lustId = 0;

    public Faculty createFaculty (Faculty faculty) {
        faculty.setId(++lustId);
        faculties.put(lustId,faculty);
        return faculty;
    }

    public Faculty findFaculty (long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
    public Faculty removeFaculty (long id) {
        return faculties.remove(id);
    }
    
}

