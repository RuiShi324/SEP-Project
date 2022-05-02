package com.example.sep.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee {
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Id
private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer sex; //0 for male, 1 for female
    private String department;
    private String birth;
    private String role;

//necessary for jpa repository
public Employee(){
    
}
    public Employee(Integer id, String name, String password, Integer age, Integer sex, String department, String role, String birth) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.department = department;
        this.role = role;
        this.birth = birth;
    }
public Employee(int id, String name, String password, String role,String department){
    this.id = id;
    this.name = name;
    this.role = role;
    this.password = password;
    this.department = department;
}


public Employee( String name, String role, String password,String department){
    
    this.name = name;
    this.role = role;
    this.password = password;
    this.department = department;

}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}



public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", department='" + department + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
/*    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                '}';
    }*/
}
