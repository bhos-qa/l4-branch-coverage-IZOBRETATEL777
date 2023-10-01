package com.example.dao.repo;

import com.example.dao.entity.Student;

import java.util.List;

public interface StudentRepo {
    Student getStudentById(Long id);
    Student getStudentByEmail(String email);
    List<Student> getAllStudents();
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Long id);
    List<Student> getStudentsByAge(Byte age);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByAgeAndName(Byte age, String name);
    List<Student> getStudentsByAgeOrName(Byte age, String name);
}
