package com.javaproject.springboot.controller;

import com.javaproject.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/get-student
    @GetMapping("get-student")
    public ResponseEntity<Student> getStudent() {
        Student student=new Student(
            1,
                "Ramesh",
                "Kumar"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student); // both statements are correct
        return ResponseEntity.ok().header("custom-header", "Swati").body(student);
    }

    // http://localhost:8080/get-list-pf-students
    @GetMapping("get-list-of-students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students=new ArrayList<>();
        students.add(new Student(1, "Swati", "Jangra"));
        students.add(new Student(2, "Abhinav", "Jangra"));
        students.add(new Student(3, "Manish", "Tomar"));

//        return students;
        return ResponseEntity.ok(students);
    }

    // SPRING BOOT REST API with Path Variable
    // http://localhost:8080/get-student-by-id/2
    @GetMapping("get-student-by-id/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId) {
        Student student= new Student(studentId , "Swati", "Jangra");
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/get-student-by-id/2/Swati/Jangra
    @GetMapping("get-student-by-id/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studnetId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        Student student= new Student(studnetId , firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // SPRING BOOT REST API with Request Param
    // http://localhost:8080/get-student-by-id?id=1
    @GetMapping("get-query-by-id")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id) {
        Student student = new Student(id, "Manish", "Tomar");
        return ResponseEntity.ok(student);
    }

    // SPRING BOOT REST API with multiple Request Params
    // http://localhost:8080/get-student-by-request-params?id=1&firstName=Manish&lastName=Tomar
    @GetMapping("get-student-by-request-params")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName,@RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
//        return new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody

    // http://localhost:8080/create-student
    @PostMapping("create-student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

//        return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    // http://localhost:8080/update-student/1
    @PutMapping("update-student/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId) {
        Student newStudent = new Student(studentId, student.getFirstName(), student.getLastName());
//        System.out.println(student.getFirstName());
//        System.out.println(student.getLastName());
        return ResponseEntity.ok(newStudent);
    }

    // Spring boot REST API that handles HTTP DELETE Request - deletes existing resource
    // http://localhost:8080/delete-student/1
    @DeleteMapping("delete-student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
//        return "Student deleted successfully!";
        return ResponseEntity.ok("Student deleted successfully!");
    }

}
