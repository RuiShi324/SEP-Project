package com.project.sep.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Employee {
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer sex; //0 for male, 1 for female
    private Department department;
    private String birth;

}
