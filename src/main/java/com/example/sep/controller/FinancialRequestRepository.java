package com.example.sep.controller;

import com.example.sep.model.FinancialRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//for connecting to the database
public interface FinancialRequestRepository extends JpaRepository<FinancialRequest, Integer>{
    List<FinancialRequest> findByRequestingDept(String requestingDept);
}



