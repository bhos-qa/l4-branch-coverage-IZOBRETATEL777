package com.example.dao.repo;

import com.example.dao.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements StudentRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public StudentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student getStudentById(Long id) {
        String sql = "SELECT s FROM Student s WHERE s.id = " + id;
        Query query = entityManager.createQuery(sql);
        return (Student) query.getSingleResult();
    }

    @Override
    public Student getStudentByEmail(String email) {
        String sql = "SELECT s FROM Student s WHERE s.email = '" + email + "'";
        Query query = entityManager.createQuery(sql);
        return (Student) query.getSingleResult();
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT s FROM Student s";
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }

    @Override
    public boolean addStudent(Student student) {
        try {
            entityManager.getTransaction().begin();
            String sql = "INSERT INTO Student s " +
                    "(s.name, s.email, s.age) " +
                    "VALUES ('" + student.getName() + "', '" + student.getEmail() + "', " + student.getAge() + ")";
            Query query = entityManager.createQuery(sql);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        try {
            entityManager.getTransaction().begin();
            String sql = "UPDATE Student s " +
                    "SET s.name = '" + student.getName() + "', " +
                    "s.email = '" + student.getEmail() + "', " +
                    "s.age = " + student.getAge() + " " +
                    "WHERE s.id = " + student.getId();
            Query query = entityManager.createQuery(sql);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(Long id) {
        try {
            entityManager.getTransaction().begin();
            String sql = "DELETE FROM Student s WHERE s.id = " + id;
            Query query = entityManager.createQuery(sql);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
