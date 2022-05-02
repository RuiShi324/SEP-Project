package com.example.sep.controller;

import com.example.sep.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//controller for financial requests
@Controller
public class FinanceController {
    @Autowired
    private FinancialRequestRepository financialRequestRepository;

    //check role and direct to the finance management page
    @RequestMapping("/financeManagement")
    public String financeManagement(HttpServletRequest request, Map<String, Object> map){
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String department = (String) session.getAttribute("department");

        if(role.equals("production manager") | role.equals("service manager")){
            // production manager and service manager can create financial requests
            map.put("createView", "createButton");
            // production manager and service manager can view their own
            // department's financial requests
            List<FinancialRequest> financialRequestList = financialRequestRepository.findByRequestingDept(department);
            map.put("financialRequestList", financialRequestList);


        }else{
            // financialmanager cannot create financial request
            map.put("createView", "hidden");
            // HR can view all the recruitment requests
            List<FinancialRequest> financialRequestList = financialRequestRepository.findAll();
            map.put("financialRequestList", financialRequestList);

        }
        return "financeManagement";
    }

    //map to the create financial request page
    @RequestMapping("/createFinance")
    public String createFinancePage(HttpServletRequest request,Map<String, Object> map){
        HttpSession session = request.getSession();
        String department = (String) session.getAttribute("department");

        map.put("department", department);

        return "createFinancialRequest";       
    }

    //get the posted value from the create financial request page
    @PostMapping(value = "/createFinancialRequest")
    public String createRecruitmentRequest( 
                               @RequestParam("requestingDept") String requestingDept,
                               @RequestParam("eventId") int eventId,
                               @RequestParam("amount") int amount,
                               @RequestParam("reason") String reason,
                               Map<String, Object> map,HttpServletRequest request) {

       FinancialRequest req = new FinancialRequest(requestingDept, eventId,amount,reason, "Draft");
        financialRequestRepository.save(req);

        return "redirect:/financeManagement";     // must use redirect here

    }

    //direct to the edit financial request page
    @PostMapping(value = "/editFinance")
    public String editTask(HttpServletRequest request, @RequestParam("financialRequestId") int financialRequestId, Map<String, Object> map){
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role.equals("financial manager")){
            map.put("commentView", "commentView");     // for financial manager, they can comment
        }else{
            map.put("commentView", "disabled");  // for other two managers, they can view the comment
        }
        FinancialRequest req = financialRequestRepository.findById(financialRequestId).get();


        map.put("finance", req);


        return "editFinancialRequest";

    }

    // click the confirm button of edit financial request
    @PostMapping(value= "/editFinancialRequest")
    public String editTaskRequest(HttpServletRequest request,
                                  @RequestParam("status") String status,
                                  @RequestParam("financialRequestID") int id,
                                  @RequestParam("commentFromFM") String commentFromFM){

        // change the status of financial request
        // Draft=>FMCommented
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("financial manager")){
            if(status.equals("Draft")){
                status = "FMCommented";
            }
        }

        // save the comment for the task in the database
        // find the task
        FinancialRequest req = financialRequestRepository.findById(id).get();
        req.setCommentFromFM(commentFromFM);
        req.setStatus(status);
        financialRequestRepository.save(req);

        return "redirect:/financeManagement";

    }
    
}
