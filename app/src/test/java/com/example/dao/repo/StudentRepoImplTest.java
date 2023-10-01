package com.example.dao.repo;

import com.example.config.JpaConfig;
import com.example.dao.entity.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;

import javax.swing.text.html.parser.Entity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRepoImplTest {
    static private StudentRepoImpl studentRepo;

    @BeforeAll
    static void setUp() {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        studentRepo = new StudentRepoImpl(entityManager);
    }

    @Test
    @Order(3)
    @DisplayName("Test getStudentById method")
    void getStudentById() {
        Student student = studentRepo.getStudentById(3L);
        assertEquals("Mary Doe", student.getName());
    }

    @Test
    @Order(4)
    @DisplayName("Test getStudentByEmail method")
    void getStudentByEmail() {
        Student student = studentRepo.getStudentByEmail("mary@mail.ru");
        assertEquals("Mary Doe", student.getName());
    }

    @Test()
    @Order(2)
    @DisplayName("Test getAllStudents method")
    void getAllStudents() {
        assertEquals(3, studentRepo.getAllStudents().size());
    }

    @Test
    @Order(1)
    @DisplayName("Test addStudent method")
    void addStudent() {
        Student student = Student.builder()
                .name("John Doe")
                .email("joe@mail.ru")
                .age((byte) 25)
                .build();
        Student student1 = Student.builder()
                .name("Jane Doe")
                .email("jane@mail.ru")
                .age((byte) 25)
                .build();
        Student student2 = Student.builder()
                .name("Mary Doe")
                .email("mary@mail.ru")
                .age((byte) 25)
                .build();
        assertTrue(studentRepo.addStudent(student));
        assertTrue(studentRepo.addStudent(student1));
        assertTrue(studentRepo.addStudent(student2));

        List<Student> students = studentRepo.getAllStudents();
        assertEquals(3, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("Jane Doe", students.get(1).getName());
        assertEquals("Mary Doe", students.get(2).getName());
    }

    @Test
    @Order(5)
    @DisplayName("Test updateStudent method")
    void updateStudent() {
        Student student = studentRepo.getStudentById(1L);
        student.setName("Bob Doe");
        assertTrue(studentRepo.updateStudent(student));
        assertEquals("Bob Doe", studentRepo.getStudentById(1L).getName());
    }

}