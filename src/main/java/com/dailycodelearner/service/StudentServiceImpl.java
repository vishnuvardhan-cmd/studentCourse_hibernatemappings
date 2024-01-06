package com.dailycodelearner.service;

import com.dailycodelearner.dto.StudentDto;
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
public class StudentServiceImpl implements StudentService {


    @Resource
    public StudentRepository studentRepository;
    @Resource
    public CourseRepository courseRepository;

    @Transactional
    @Override
    public StudentDto addStudentDto(StudentDto studentDto) {
        Student student = new Student();
        mapDtoToEntity(studentDto,student);
        Student savedStudent=studentRepository.save(student);
        return mapEntityToDto(savedStudent);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtos=new ArrayList<>();
        List<Student> students=studentRepository.findAll();
        students.stream().forEach(student->{
            studentDtos.add(mapEntityToDto(student));
        });
        return studentDtos;
    }


    @Transactional
    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student=studentRepository.findById(id).get();
        mapDtoToEntity(studentDto,student);
        Student savedStudent=studentRepository.save(student);
        return mapEntityToDto(savedStudent);
    }

    @Transactional
    @Override
    public String deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        //Remove the related courses from student entity.
        if(student.isPresent()) {
            student.get().removeCourses();
            studentRepository.deleteById(student.get().getId());
            return "Student with id: " + id + " deleted successfully!";
        }
        return null;
    }

    private void mapDtoToEntity(StudentDto studentDto, Student student) {
        student.setName(studentDto.getName());
        if(null==student.getCourses()){
            student.setCourses(new HashSet<>());
        }
        studentDto.getCourses().stream().forEach(courseName->{
            Course course=courseRepository.findByName(courseName);
            if(null==course){
                course=new Course();
                course.setStudents(new HashSet<>());
            }
            course.setName(courseName);
            student.addCourse(course);
        });
    }

    private StudentDto mapEntityToDto(Student savedStudent) {
        StudentDto studentDto=new StudentDto();
        studentDto.setId(savedStudent.getId());
        studentDto.setName(savedStudent.getName());
        studentDto.setCourses(savedStudent.getCourses().stream().map(Course::getName).collect(Collectors.toSet()));
        return studentDto;
    }
}
