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
            entityManager.persist(student);
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
            entityManager.merge(student);
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
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
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
