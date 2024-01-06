package com.dailycodelearner.service;

import com.dailycodelearner.dto.CourseDto;
import com.dailycodelearner.entity.Course;
import com.dailycodelearner.entity.Student;
import com.dailycodelearner.repository.CourseRepository;
import com.dailycodelearner.repository.StudentRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseRepository courseRepository;

    @Resource
    StudentRepository studentRepository;

    @Transactional
    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        Course course = new Course();
        mapDtoToEntity(course, courseDto);
        Course saveCourse=courseRepository.save(course);
        return mapEntityToDto(saveCourse);
    }



    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        List<CourseDto> courseDtos=new ArrayList<>();
        courseList.stream().forEach(course->{
            CourseDto courseDto=mapEntityToDto(course);
            courseDtos.add(courseDto);
        });
        return courseDtos;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        if(byId.isPresent()){
            return mapEntityToDto(byId.get());
        }
        CourseDto courseDto=new CourseDto();
        courseDto.setName("course doesn't exsist with given id");
        return courseDto;
    }

    @Transactional
    @Override
    public CourseDto updateCourseById(Long id, CourseDto courseDto) {
        Optional<Course> course=courseRepository.findById(id);
        if(course.isPresent()){
            Course course1=course.get();
            mapDtoToEntity(course1,courseDto);
            return mapEntityToDto(courseRepository.save(course1));
        }
        CourseDto courseDto1=new CourseDto();
        courseDto1.setName("course doesn't exsist with given id");
        return courseDto1;
    }

    @Transactional
    @Override
    public String deleteCourseById(Long id) {
        Optional<Course> byId = courseRepository.findById(id);
        if(byId.isPresent()){
            byId.get().removeStudents();
            courseRepository.delete(byId.get());
            return "successfully deleted the id : "+id;
        }
        return "course with the given id : "+" is not founded";
    }

    private void mapDtoToEntity(Course course, CourseDto courseDto) {
        course.setName(courseDto.getName());
        if (null == course.getStudents()) {
            course.setStudents(new HashSet<>());
        }
        courseDto.getStudents().stream().forEach(studentname -> {
            Student student = studentRepository.findByName(studentname);
            if (student == null) {
                student = new Student();
                student.setName(studentname);
                student.setCourses(new HashSet<>());
            }
            course.setName(courseDto.getName());
            course.addStudent(student);
        });
    }

    private CourseDto mapEntityToDto(Course saveCourse) {
        CourseDto courseDto=new CourseDto();
        courseDto.setId(saveCourse.getId());
        courseDto.setName(saveCourse.getName());
        courseDto.setStudents(saveCourse.getStudents().stream().map(Student::getName).collect(Collectors.toList()));
        return courseDto;
    }
}
