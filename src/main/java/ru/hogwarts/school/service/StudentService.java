package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Lust5Students;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService {


   private final StudentRepository studentRepository;

   public StudentService(StudentRepository studentRepository) {
      this.studentRepository = studentRepository;
   }

   public Collection<Student> getAllStudents(){
      return studentRepository.findAll();
   }
   public Student createStudent (Student student) {
      return studentRepository.save(student);
   }

   public Student findStudent(long id){
      return studentRepository.findById(id).get();
   }


   public Student editStudent (Student student) {
      return studentRepository.save(student);
   }
   public void removeStudent (long id){
      studentRepository.deleteById(id);
   }
   public Collection <Student> findByAgeBetween (int min, int max){
      return studentRepository.findByAgeBetween(min, max);
   }
   public Faculty getFacultyByName(String name) {
      return studentRepository.findStudentByName(name).getFaculty();
   }

   public Integer getNumberOfStudents(){
      return studentRepository.getNumberOfStudents();
   }

   public Integer getAverageAgeOfStudents(){
      return studentRepository.getAverageAgeOfStudents();
   }

   public List<Lust5Students> getLust5Students(){
      return studentRepository.getLust5Students();
   }
}