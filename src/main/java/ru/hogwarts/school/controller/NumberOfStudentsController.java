package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.StudentService;

@RestController
public class NumberOfStudentsController {
    private final StudentService studentService;

    public NumberOfStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-nubber-of-students")
    public Integer getNumberOfStudents() {
        return studentService.getNumberOfStudents();
    }
}
