package com.dailycodelearner.repository;

import com.dailycodelearner.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    public Student findByName(String name);
}
