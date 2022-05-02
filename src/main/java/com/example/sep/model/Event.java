package com.example.sep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int clientId;
    private String clientName;
    private String eventType;
    private String beginDate;
    private String endDate;
    private String preferences;
    private int expectedBudget;
    private String status;
    private String comment;
    private String description;


    public Event() {
    }


    public Event(String clientName, String eventType, String beginDate, String endDate,
                 String preferences, int expectedBudget, String description) {
        this.clientId = 0;
        this.clientName = clientName;
        this.eventType = eventType;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.preferences = preferences;
        this.expectedBudget = expectedBudget;
        this.status = "Created";
        this.description = description;
    }

    public Event(int id, int clientId, String clientName, String eventType, String beginDate, String endDate, String preferences, int expectedBudget, String status, String comment, String description) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.eventType = eventType;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.preferences = preferences;
        this.expectedBudget = expectedBudget;
        this.status = status;
        this.comment = comment;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public int getExpectedBudget() {
        return expectedBudget;
    }

    public void setExpectedBudget(int expectedBudget) {
        this.expectedBudget = expectedBudget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", preferences='" + preferences + '\'' +
                ", expectedBudget=" + expectedBudget +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



}