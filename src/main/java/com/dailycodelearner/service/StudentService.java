package com.dailycodelearner.service;

import com.dailycodelearner.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto addStudentDto(StudentDto studentDto);
    public List<StudentDto> getAllStudents();

    public StudentDto updateStudent(Long id,StudentDto studentDto);
    public String deleteStudent(Long id);
}
