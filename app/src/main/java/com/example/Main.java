package com.example;

import com.example.config.JpaConfig;
import com.example.dao.entity.Student;
import com.example.dao.repo.StudentRepo;
import com.example.dao.repo.StudentRepoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = JpaConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        StudentRepo studentRepo = new StudentRepoImpl(em);
        System.out.println("All students: " + studentRepo.getAllStudents());

        Student student = Student.builder()
                .name("John Doe")
                .email("joe.doe@mail.ru")
                .age((byte) 25)
                .build();
        studentRepo.addStudent(student);
        System.out.println("All students: " + studentRepo.getAllStudents());


        Student student1 = studentRepo.getStudentById(1L);
        student1.setName("Jane Doe");
        studentRepo.updateStudent(student1);
        System.out.println("All students: " + studentRepo.getAllStudents());


        Student student2 = Student.builder()
                .name("Mary Doe")
                .email("mary.doe@mail.ru")
                .age((byte) 21)
                .build();

        studentRepo.addStudent(student2);
        System.out.println("All students: " + studentRepo.getAllStudents());


        studentRepo.deleteStudent(1L);
        System.out.println("All students: " + studentRepo.getAllStudents());

        em.close();
        JpaConfig.closeEntityManagerFactory(emf);

    }
}
