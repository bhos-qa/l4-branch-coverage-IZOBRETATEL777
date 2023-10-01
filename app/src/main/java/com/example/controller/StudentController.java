package com.example.controller;

import com.example.dao.entity.Student;
import com.example.dao.repo.StudentRepo;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class StudentController {
    private final StudentRepo studentRepo;
    private final Scanner scanner;

    public void getAllStudents() {
        System.out.println("All students: " + studentRepo.getAllStudents());
    }

    public void addStudent() {
        Student student = new Student();
        System.out.println("Enter student name: ");
        student.setName(scanner.nextLine());
        System.out.println("Enter student email: ");
        student.setEmail(scanner.nextLine());
        System.out.println("Enter student age: ");
        while (!scanner.hasNextByte()) {
            System.out.println("Please enter a valid byte for age: ");
            scanner.next();
        }
        student.setAge(scanner.nextByte());
        scanner.nextLine();
        studentRepo.addStudent(student);
        System.out.println("All students: " + studentRepo.getAllStudents());
    }

    public void updateStudent() {
        Student student = new Student();
        System.out.println("Enter student id: ");
        student.setId(scanner.nextLong());
        System.out.println("Enter student name: ");
        student.setName(scanner.nextLine());
        System.out.println("Enter student email: ");
        student.setEmail(scanner.nextLine());
        while (!scanner.hasNextByte()) {
            System.out.println("Enter student age: ");
            scanner.next();
        }
        student.setAge(scanner.nextByte());
        scanner.nextLine();
        studentRepo.updateStudent(student);
        System.out.println("All students: " + studentRepo.getAllStudents());
    }


    public void deleteStudent() {
        System.out.println("Enter student id: ");
        studentRepo.deleteStudent(scanner.nextLong());
        System.out.println("All students: " + studentRepo.getAllStudents());
    }

    public void getStudentById() {
        System.out.println("Enter student id: ");
        System.out.println("Student: " + studentRepo.getStudentById(scanner.nextLong()));
    }

    public void getStudentByEmail() {
        System.out.println("Enter student email: ");
        System.out.println("Student: " + studentRepo.getStudentByEmail(scanner.nextLine()));
    }
}
