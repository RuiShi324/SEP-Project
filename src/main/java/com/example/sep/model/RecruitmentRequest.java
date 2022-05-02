package com.example.sep.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RecruitmentRequest {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private String requestingDept;
    private String contractType;
    private String yearOfExperience;
    private String jobTitle;
    private String description;
    private String status;
    private String commentFromHR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestingDept() {
        return requestingDept;
    }

    public void setRequestingDept(String requestingDept) {
        this.requestingDept = requestingDept;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(String yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommentFromHR() {
        return commentFromHR;
    }

    public void setCommentFromHR(String commentFromHR) {
        this.commentFromHR = commentFromHR;
    }

    public RecruitmentRequest(int id, String requestingDept, String contractType, String yearOfExperience,
            String jobTitle, String description, String status) {
        this.id = id;
        this.requestingDept = requestingDept;
        this.contractType = contractType;
        this.yearOfExperience = yearOfExperience;
        this.jobTitle = jobTitle;
        this.description = description;
        this.status = status;
//        this.commentFromHR = commentFromHR;
    }

    public RecruitmentRequest(String requestingDept, String contractType, String yearOfExperience, String jobTitle,
            String description, String status) {
        this.requestingDept = requestingDept;
        this.contractType = contractType;
        this.yearOfExperience = yearOfExperience;
        this.jobTitle = jobTitle;
        this.description = description;
        this.status = status;
    }

    public RecruitmentRequest(int id, String requestingDept, String contractType, String yearOfExperience, String jobTitle, String description, String status, String commentFromHR) {
        this.id = id;
        this.requestingDept = requestingDept;
        this.contractType = contractType;
        this.yearOfExperience = yearOfExperience;
        this.jobTitle = jobTitle;
        this.description = description;
        this.status = status;
        this.commentFromHR = commentFromHR;
    }

    @Override
    public String toString() {
        return "RecruitmentRequest{" +
                "id=" + id +
                ", requestingDept='" + requestingDept + '\'' +
                ", contractType='" + contractType + '\'' +
                ", yearOfExperience='" + yearOfExperience + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", commentFromHR='" + commentFromHR + '\'' +
                '}';
    }

    public RecruitmentRequest(){

        
    }
}
