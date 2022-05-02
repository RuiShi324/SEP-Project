package com.example.sep.controller;


import com.example.sep.model.RecruitmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//for connecting to the database
public interface RecruitmentRequestRepository extends JpaRepository<RecruitmentRequest, Integer>{

    List<RecruitmentRequest> findByRequestingDept(String requestingDept);
}
