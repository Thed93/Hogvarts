package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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
    public ResponseEntity <Collection<Student>> getAllStudents(){
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
    @GetMapping("/filter")
    public ResponseEntity <Collection<Student>> findByAgeBetween(@RequestParam int from, @RequestParam int to){
        return ResponseEntity.ok(studentService.findByAgeBetween(from, to));
    }
    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getFacultyStudent (@RequestParam String name) {
        return ResponseEntity.ok(studentService.getFacultyByName(name));

    }

    @PostMapping(value = "/{id}/avatars", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <String> uploadAvatar (@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException{
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("file is too big");
        }
        studentService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}