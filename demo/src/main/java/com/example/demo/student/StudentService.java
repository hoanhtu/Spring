package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findStudentByEmail(String email) {
//        Optional<Student> student = studentRepository.findStudentByEmail(email);
//        if(student.isPresent())
//        {
//            System.out.println("found ok");
//            return student.get();
//        }

        return studentRepository.findStudentByEmail(email).orElse(null);
    }
}
