package com.example.sep.controller;

import com.example.sep.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//for connection to the database
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    public List<Employee> findByName(String name);
    public List<Employee> findByDepartment(String department);
    public List<Employee> findById(int id);

}



