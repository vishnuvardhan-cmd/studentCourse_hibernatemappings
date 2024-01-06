package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Course")
@Getter
@Setter
public class Course {

    @Column(name="CourseId")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "courses",fetch = FetchType.LAZY)
    private Set<Student> students;

    public void addStudent(Student student){
        this.students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
        student.getCourses().remove(this);
    }

    public void removeStudents(){
        for(Student student:new HashSet<>(students)){
            removeStudent(student);
        }
    }
//    cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY,
}
