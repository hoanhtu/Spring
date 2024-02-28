package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    public StudentController() {
    }

    @GetMapping("/info")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping("/find")
    public void findStudentByEmail(String email) {
        Student student = studentService.findStudentByEmail(email);
        if (student != null) {
            System.out.println(student);
        } else System.out.println(" not found " + email);
    }
}
