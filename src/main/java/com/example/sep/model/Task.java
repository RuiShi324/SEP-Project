package com.example.sep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

@GeneratedValue(strategy= GenerationType.IDENTITY)
@Id
private int id;
private int eventId;
private int employeeId;
private String employeeName;
private String subject;
private String description;
private int senderId;
private int budget;
private String department;
private String priority;
private String plan;
private String comment;
private String status;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", senderId=" + senderId +
                ", budget=" + budget +
                ", department='" + department + '\'' +
                ", priority='" + priority + '\'' +
                ", plan='" + plan + '\'' +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Task(int id, int eventId, int employeeId, String employeeName, String subject, String description, int senderId, int budget, String department, String priority, String plan, String comment, String status) {
        this.id = id;
        this.eventId = eventId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.subject = subject;
        this.description = description;
        this.senderId = senderId;
        this.budget = budget;
        this.department = department;
        this.priority = priority;
        this.plan = plan;
        this.comment = comment;
        this.status = status;
    }

    public Task(){

    
}

public Task(int id, int eventId, int employeeId,String employeeName, String subject, 
String description, int sender, String department, String priority, 
String status,String plan,String comment,int budget) {
    this.id = id;
    this.eventId = eventId;
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.department = department;
    this.subject = subject;
    this.description = description;
    this.senderId = sender;
    this.priority = priority;
    this.status = status;
    this.plan = plan;
    this.comment = comment;
    this.budget = budget;
}

public Task(int eventId, int employeeId, String subject, 
String description, int sender, int budget, String department, String priority, String status,String employeeName) {
    this.eventId = eventId;
    this.employeeId = employeeId;
    this.department = department;
    this.subject = subject;
    this.description = description;
    this.senderId = sender;
    this.priority = priority;
    this.status = status;
    this.employeeName  = employeeName;
    this.budget = budget;
    
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getEventId() {
    return eventId;
}

public void setEventId(int eventId) {
    this.eventId = eventId;
}

public int getEmployeeId() {
    return employeeId;
}

public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
}

public String getSubject() {
    return subject;
}

public void setSubject(String subject) {
    this.subject = subject;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public int getSenderId() {
    return senderId;
}

public void setSenderId(int senderId) {
    this.senderId = senderId;
}

public String getPriority() {
    return priority;
}

public void setPriority(String priority) {
    this.priority = priority;
}

public String getPlan() {
    return plan;
}

public void setPlan(String plan) {
    this.plan = plan;
}

public String getComment() {
    return comment;
}

public void setComment(String comment) {
    this.comment = comment;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}

public String getEmployeeName() {
    return employeeName;
}

public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
}

public int getBudget() {
    return budget;
}

public void setBudget(int budget) {
    this.budget = budget;
}


}
