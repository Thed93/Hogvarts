SELECT student.name, student.age, faculty.name FROM student INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name FROM student RIGHT JOIN avatar a on student.id = a.student_id;