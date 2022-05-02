package com.example.sep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinancialRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String requestingDept;
    private int eventId;
    private int amount;
    private String reason;
    private String status;
    private String commentFromFM;


    public FinancialRequest(int id, String requestingDept, int eventId, int amount,
                            String reason, String status, String commentFromFM) {
        this.id = id;
        this.requestingDept = requestingDept;
        this.eventId = eventId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.commentFromFM = commentFromFM;
    }

    public FinancialRequest(String requestingDept, int eventId, int amount,
                            String reason, String status) {

        this.requestingDept = requestingDept;
        this.eventId = eventId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public FinancialRequest() {

    }


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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommentFromFM() {
        return commentFromFM;
    }

    public void setCommentFromFM(String commentFromFM) {
        this.commentFromFM = commentFromFM;
    }

    @Override
    public String toString() {
        return "FinancialRequest{" +
                "id=" + id +
                ", requestingDept='" + requestingDept + '\'' +
                ", eventId=" + eventId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", commentFromFM='" + commentFromFM + '\'' +
                '}';
    }
}
