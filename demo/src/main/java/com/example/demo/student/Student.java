package com.example.demo.student;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

//    @Column(name = "name")
//    String nameReal;
    String email;
    int age;

    public Student(long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }


    public Student() {

    }
}
