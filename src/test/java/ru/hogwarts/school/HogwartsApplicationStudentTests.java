package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HogwartsApplicationStudentTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentInfo() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/id", String.class))
                .isNotNull();
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/id", student, String.class))
                .isNotNull();
    }

    @Test
    void testDeleteStudentInfo() throws Exception {
        Student student = new Student();
        student.setId(4L);
        student.setName("Bob");
        HttpEntity<Student> student1 = new HttpEntity<>(student);
        ResponseEntity<Student> studentEntity = restTemplate.exchange("http://localhost:" + port + "/students/4",
                HttpMethod.DELETE, student1, Student.class);
        Assertions
                .assertThat(studentEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testEditStudentInfo() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(22);
        HttpEntity<Student> student1 = new HttpEntity<>(student);
        ResponseEntity<Student> studentEntity = restTemplate.exchange("http://localhost:" + port + "/students",
                HttpMethod.PUT, student1, Student.class);
        Assertions
                .assertThat(studentEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

}