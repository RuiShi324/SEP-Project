package com.project.sep.dao;

import com.project.sep.model.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer, Department>();
        departments.put(001, new Department(001,"CSO"));
        departments.put(002, new Department(002,"MO"));
        departments.put(003, new Department(003,"HRTO"));
        departments.put(004, new Department(004,"SCSO"));
        departments.put(005, new Department(005,"FD"));
        departments.put(006, new Department(006,"SD"));
        departments.put(007, new Department(007,"PD"));
    }

    // collect information of department
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //get the department by id
    public Department getDepartment(Integer id){
        return departments.get(id);
    }
}
