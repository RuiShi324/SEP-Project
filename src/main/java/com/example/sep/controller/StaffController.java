package com.example.sep.controller;

import com.example.sep.model.RecruitmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

//controller for recruitment requests
@Controller
public class StaffController {
    @Autowired
    private com.example.sep.controller.RecruitmentRequestRepository recruitmentRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    //direct to the staff management page and check the role
    @RequestMapping("/employeeManagement")
    public String employeeManagement(HttpServletRequest request, Map<String, Object> map){
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
//        int userId = (int) session.getAttribute("userId");
        String department = (String) session.getAttribute("department");

        if(role.equals("production manager") | role.equals("service manager")){
            // production manager and service manager can create recruitment requests
            map.put("createView", "createButton");
            // production manager and service manager can view their own
            // department's recruitment requests
            List<RecruitmentRequest> recruitmentList = recruitmentRequestRepository.findByRequestingDept(department);
            map.put("recruitmentList", recruitmentList);


        }else{
            // HR cannot create recruitment request
            map.put("createView", "hidden");
            // HR can view all the recruitment requests
            List<RecruitmentRequest> recruitmentList = recruitmentRequestRepository.findAll();
            map.put("recruitmentList", recruitmentList);

        }
        return "recruitmentManagement";
    }

    //direct to the create recruitment request page
    @RequestMapping("/createRecruitment")
    public String createRecruitmentPage(HttpServletRequest request,Map<String, Object> map){
        HttpSession session = request.getSession();
        String department = (String) session.getAttribute("department");

        map.put("department", department);

        return "createRecruitmentRequest";        // go to createRecruitmentRequest.html
    }

    //get the value from the recruitment request page
    @PostMapping(value = "/createRecruitmentRequest")
    public String createRecruitmentRequest( @RequestParam("contractType") String contractType,
                               @RequestParam("requestingDept") String requestingDept,
                               @RequestParam("yearOfExperience") String yearOfExperience,
                               @RequestParam("jobTitle") String jobTitle,
                               @RequestParam("description") String description,
                               Map<String, Object> map,HttpServletRequest request) {

        RecruitmentRequest req = new RecruitmentRequest(requestingDept, contractType, yearOfExperience, jobTitle,
                description, "Draft");
        recruitmentRequestRepository.save(req);

        return "redirect:/employeeManagement";     // must use redirect here

    }

    //direct to the edit recruitment page accroding to the role
    @PostMapping(value = "/editRecruitment")
    public String editTask(HttpServletRequest request, @RequestParam("recruitmentId") String recruitmentId, Map<String, Object> map){
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role.equals("HR manager")){
            map.put("commentView", "commentView");     // for HR, they can comment
        }else{
            map.put("commentView", "disabled");  // for other two managers, they can view the HR comment
        }
        RecruitmentRequest req = recruitmentRequestRepository.findById(parseInt(recruitmentId)).get();


        map.put("recruitment", req);


        return "editRecruitmentRequest";

    }

    // click the confirm button of edit recruitment request
    @PostMapping(value= "/editRecruitmentRequest")
    public String editTaskRequest(HttpServletRequest request,
                                  @RequestParam("status") String status,
                                  @RequestParam("recruitmentID") String id,
                                  @RequestParam("commentFromHR") String commentFromHR){

        // change the status of recruitment
        // Draft=>HRCommented
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("HR manager")){
            if(status.equals("Draft")){
                status = "HRCommented";
            }
        }

        // save the comment for the task in the database
        // find the task
        RecruitmentRequest req = recruitmentRequestRepository.findById(parseInt(id)).get();
        req.setCommentFromHR(commentFromHR);
        req.setStatus(status);
        recruitmentRequestRepository.save(req);

        return "redirect:/employeeManagement";

    }

    //for inserting a few test data into the database
    @RequestMapping(value = "/TestDatabase")
    public String insertTestRecruitment(){
        List<RecruitmentRequest> recruitmentList = new ArrayList<RecruitmentRequest>();
        recruitmentList.add(new RecruitmentRequest(1, "production", "part-time",
                "at least 2", "photographer", "xxx", "Draft"));
        recruitmentList.add(new RecruitmentRequest("service", "full-time",
                "at least 5", "Top Chef", "xxx", "Draft"));
        for(RecruitmentRequest t : recruitmentList){
            recruitmentRequestRepository.save(t);
        }

        return "redirect:success";
    }

}
