package com.example;

import com.example.config.JpaConfig;
import com.example.controller.StudentController;
import com.example.dao.entity.Student;
import com.example.dao.repo.StudentRepo;
import com.example.dao.repo.StudentRepoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = JpaConfig.getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();
        StudentRepo studentRepo = new StudentRepoImpl(entityManager);
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController(studentRepo, scanner);

        studentController.getAllStudents();
        studentController.addStudent();
        studentController.addStudent();
        studentController.addStudent();
        studentController.updateStudent();
        studentController.deleteStudent();
        studentController.getStudentById();
        studentController.getStudentByEmail();

        entityManager.close();
        JpaConfig.closeEntityManagerFactory(emf);

    }
}
