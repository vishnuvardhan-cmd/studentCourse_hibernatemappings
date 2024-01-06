package com.dailycodelearner.controller;

import com.dailycodelearner.dto.CourseDto;
import com.dailycodelearner.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto){
        CourseDto courseDto1 = courseService.addCourse(courseDto);
        return new ResponseEntity<>(courseDto1, HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return  new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") Long id){
        return  new ResponseEntity<>(courseService.getCourseById(id),HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourseById(@PathVariable("id")Long id,@RequestBody CourseDto courseDto){
       return new ResponseEntity<>(courseService.updateCourseById(id,courseDto),HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(courseService.deleteCourseById(id),HttpStatus.OK);
    }
}
