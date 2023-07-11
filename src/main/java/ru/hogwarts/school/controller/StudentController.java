package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo (@PathVariable long id){
        Student student = studentService.findStudent(id);
        if (student == null){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Student> getFacultyByStudentId (@PathVariable long id){
        Student student = studentService.findFacultyByStudentId(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity <Collection<Student>> getAllStudents(@RequestParam (required = false) Integer min, @RequestParam (required = false) Integer max){
        if (max > 0 && min > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student){
        Student findStudent = studentService.editStudent(student);
        if (student == null){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent (@PathVariable long id){
      studentService.removeStudent(id);
      return ResponseEntity.ok().build();
    }
}
