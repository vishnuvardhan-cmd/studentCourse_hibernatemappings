package com.dailycodelearner.service;

import com.dailycodelearner.dto.CourseDto;
import com.dailycodelearner.entity.Course;

import java.util.List;

public interface CourseService {

    public CourseDto addCourse(CourseDto courseDto);
    public List<CourseDto> getAllCourses();

    public CourseDto getCourseById(Long id);
    public CourseDto updateCourseById(Long id,CourseDto courseDto);

    public String deleteCourseById(Long id);

}
