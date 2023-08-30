package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
public class Lust5Students {
    private final StudentService studentService;

    public Lust5Students(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/get-lust-5-students")
    public List<ru.hogwarts.school.model.Lust5Students> getLust5Students() {
        return studentService.getLust5Students();
    }
}

