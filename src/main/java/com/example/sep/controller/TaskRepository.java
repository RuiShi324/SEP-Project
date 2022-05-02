package com.example.sep.controller;

import com.example.sep.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//for connecting to the database
public interface TaskRepository extends JpaRepository<Task, Integer>{
    public List<Task> findByDepartment(String department);
    public List<Task> findById(int id);
    public List<Task> findByEmployeeId(int id);
}
