package com.javaproject.springboot.controller;

import com.javaproject.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/get-student
    @GetMapping("get-student")
    public Student getStudent() {
        Student student=new Student(
            1,
                "Ramesh",
                "Kumar"
        );
        return student;
    }

    // http://localhost:8080/get-list-pf-students
    @GetMapping("get-list-of-students")
    public List<Student> getStudents() {
        List<Student> students=new ArrayList<>();
        students.add(new Student(1, "Swati", "Jangra"));
        students.add(new Student(2, "Abhinav", "Jangra"));
        students.add(new Student(3, "Manish", "Tomar"));

        return students;
    }

    // SPRING BOOT REST API with Path Variable
    // http://localhost:8080/get-student-by-id/2
    @GetMapping("get-student-by-id/{id}")
    public Student studentPathVariable(@PathVariable("id") int studnetId) {
        return new Student(studnetId , "Swati", "Jangra");
    }

    // http://localhost:8080/get-student-by-id/2/Swati/Jangra
    @GetMapping("get-student-by-id/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studnetId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        return new Student(studnetId , firstName, lastName);
    }

    // SPRING BOOT REST API with Request Param
    // http://localhost:8080/get-student-by-id?id=1
    @GetMapping("get-query-by-id")
    public Student studentRequestVariable(@RequestParam int id) {
        return new Student(id, "Manish", "Tomar");
    }

    // SPRING BOOT REST API with multiple Request Params
    // http://localhost:8080/get-student-by-request-params?id=1&firstName=Manish&lastName=Tomar
    @GetMapping("get-student-by-request-params")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName,@RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody

    // http://localhost:8080/create-student
    @PostMapping("create-student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    // http://localhost:8080/update-student/1
    @PutMapping("update-student/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring boot REST API that handles HTTP DELETE Request - deletes existing resource
    // http://localhost:8080/delete-student/1
    @DeleteMapping("delete-student/{id}")
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return "Student deleted successfully!";
    }

}
