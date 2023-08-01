package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

   private final Map<Long, Student> students = new HashMap<>();

   private  long lustId = 0;

   public Collection<Student> getAllStudents(){
      return students.values();
   }

   public Student createStudent (Student student) {
      student.setId(++lustId);
      students.put(lustId,student);
      return student;
   }

   public Student findStudent (long id){
      return students.get(id);
   }

   public Student editStudent (Student student) {
      if (students.containsKey(student.getId())) {
         students.put(student.getId(), student);
         return student;
      }
      return null;
   }

   public Student removeStudent (long id){
      return students.remove(id);
   }

}
