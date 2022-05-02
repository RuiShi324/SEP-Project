package com.project.sep.dao;

import com.project.sep.model.Department;
import com.project.sep.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(001, new Employee( 001,"Janet", "2207", 32, 1, new Department(001, "CSO" ) ,"1997.05.15"));
    }
}
