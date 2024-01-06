package com.dailycodelearner.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentDto {
    private Long id;
    private String name;
    private Set<String> courses = new HashSet<>();
}
