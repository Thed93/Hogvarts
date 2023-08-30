package ru.hogwarts.school.service.production;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Lust5Students;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceProduction implements StudentService {


   private final StudentRepository studentRepository;

   public StudentServiceProduction(StudentRepository studentRepository) {
      this.studentRepository = studentRepository;
   }

   Logger logger = LoggerFactory.getLogger(StudentServiceProduction.class);

   public Collection<Student> getAllStudents(){
      logger.debug("Вызван метод 'getAllStudents'");
      return studentRepository.findAll();
   }
   public Student createStudent (Student student) {
      logger.debug("Вызван метод 'createStudent'");
      return studentRepository.save(student);
   }

   public Student findStudent(long id){
      logger.debug("Вызван метод 'findStudent'");
      return studentRepository.findById(id).get();
   }


   public Student editStudent (Student student) {
      logger.debug("Вызван метод 'editStudent'");
      return studentRepository.save(student);
   }
   public void removeStudent (long id){
      logger.debug("Вызван метод 'removeStudent'");
      studentRepository.deleteById(id);
   }
   public Collection <Student> findByAgeBetween (int min, int max){
      logger.debug("Вызван метод 'findByAgeBetween'");
      return studentRepository.findByAgeBetween(min, max);
   }
   public Faculty getFacultyByName(String name) {
      logger.debug("Вызван метод 'getFacultyByName'");
      return studentRepository.findStudentByName(name).getFaculty();
   }

   public Integer getNumberOfStudents(){
      logger.debug("Вызван метод 'getNumberOfStudents'");
      return studentRepository.getNumberOfStudents();
   }

   public Integer getAverageAgeOfStudents(){
      logger.debug("Вызван метод 'getAverageAgeOfStudents'");
      return studentRepository.getAverageAgeOfStudents();
   }

   public List<Lust5Students> getLust5Students(){
      logger.debug("Вызван метод 'getLust5Students'");
      return studentRepository.getLust5Students();
   }

   public List<String> getStudentsStartWithA(){
     return studentRepository.findAll().stream()
              .map(Student->Student.getName().toUpperCase())
             .filter(name->name.startsWith("A"))
             .sorted()
             .collect(Collectors.toList());
   }

   public double getAvgAge() {
      return studentRepository.findAll().stream()
              .mapToDouble(Student::getAge)
              .average()
              .getAsDouble();
   }

   public Integer sumImpr() {
      long start = System.currentTimeMillis();
      int res = Stream.iterate(1, a -> a +1)
              .parallel()
              .limit(1_000_000)
              .reduce(0, Integer::sum);
      long finish = System.currentTimeMillis();
      long dif = finish - start;
      System.out.println("impr " + dif);
      return res;

   }

   public Integer sumIm() {
      long start = System.currentTimeMillis();
      int res = Stream.iterate(1, a -> a + 1)
              .limit(1_000_000)
              .reduce(0, Integer::sum);
      long finish = System.currentTimeMillis();
      long dif = finish - start;
      System.out.println("im " + dif);
      return res;
   }


   }
