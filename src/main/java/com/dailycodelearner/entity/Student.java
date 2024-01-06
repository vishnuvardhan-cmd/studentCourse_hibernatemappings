package com.dailycodelearner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="StudentId")
    private Long id;
    private String name;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable(name="Student_Course",joinColumns = {
            @JoinColumn(name="StudentId",referencedColumnName = "StudentId")
    },inverseJoinColumns = {
            @JoinColumn(name="CourseId",referencedColumnName = "CourseId")
    })
    private Set<Course> courses;

    public void addCourse(Course course){
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getStudents().remove(this);
    }

    public void removeCourses(){
        for(Course course:new HashSet<>(courses)){
            removeCourse(course);
        }
    }

}
