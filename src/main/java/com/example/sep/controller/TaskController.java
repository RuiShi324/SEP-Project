package com.example.sep.controller;

import com.example.sep.model.Employee;
import com.example.sep.model.Task;
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

//controller for the task
@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    //direct to the task management page according to the role
    @RequestMapping("/taskManagement")
    public String eventManagement(HttpServletRequest request,Map<String, Object> map) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        int userId = (int) session.getAttribute("userId");
        String department = (String) session.getAttribute("department");
        
        if(role.equals("production manager")|role.equals("service manager")){
            // production manager and service manager can create tasks
            session.setAttribute("createTaskEnable", "createButton");
            List<Task> taskList = taskRepository.findByDepartment(department);
            map.put("tasklist", taskList);
        }else{
            //service and production team member can not create tasks
            session.setAttribute("createTaskEnable", "hidden");
            //view and edit their own tasks
            List<Task> taskList = taskRepository.findByEmployeeId(userId);
            map.put("tasklist", taskList);
        }
       
       

       
        return "taskManagement";
    }

    //direct to the create task request page
    @RequestMapping("/createTask")
    public String createTaskPage(HttpServletRequest request,Map<String, Object> map){
        HttpSession session = request.getSession();
        String dep = (String) session.getAttribute("department");
        List<Employee> employeeList = employeeRepository.findByDepartment(dep);

        map.put("employeelist",employeeList);

        return "createTaskRequest";        // go to createTaskRequest.html
    }

    //get the value from the create task request page
    @PostMapping(value = "/createTaskRequest")
    public String createTask( @RequestParam("eventId") int eventId, @RequestParam("employeeId") int employeeId,
                              @RequestParam("subject") String subject, @RequestParam("description") String description,
                              @RequestParam("priority") String priority, @RequestParam("budget") int budget,
                              Map<String, Object> map,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String employeeName = employeeRepository.findById(employeeId).get(0).getName();

        Task task = new Task(eventId,employeeId,subject,description,(int) session.getAttribute("userId"),budget,
        (String)session.getAttribute("department"),priority,"Created",employeeName);

        taskRepository.save(task);

        return "redirect:/taskManagement";

    }

    //direct to the edit task page
    @PostMapping(value = "/editTask")
    public String editTask(HttpServletRequest request, @RequestParam("taskId") String taskId, Map<String, Object> map){
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role.equals("production manager")|role.equals("service manager")){
            map.put("commentView", "disabled");     // for managers, they can only view the comment
        }else{
            map.put("commentView", "commentView");  // for ordinary team members, they can comment
        }
        // System.out.println(taskId);
        Task task = taskRepository.findById(parseInt(taskId)).get(0);

        map.put("task", task);


        return "editTaskRequest";

    }

    // click the confirm button of editing task
    @PostMapping(value= "/editTaskRequest")
    public String editTaskRequest(HttpServletRequest request, Map<String, Object> map,
                                  @RequestParam("status") String status,
                                  @RequestParam("taskId") String taskId,
                                  @RequestParam("comment") String comment,
                                  @RequestParam("plan") String plan){

        // Draft=>Edited=>Checked
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("production manager")|role.equals("service manager")){
            if(status.equals("Edited")){
                status = "Checked";
            }
        }else{
            if(status.equals("Draft")){
                status = "Edited";
            }
        }

        // save the comment for the task in the database
        // find the task
        Task task = taskRepository.findById(parseInt(taskId)).get(0);
        task.setComment(comment);
        task.setStatus(status);
        task.setPlan(plan);
        taskRepository.save(task);

        return "redirect:/taskManagement";

    }



     //for insert a few data into the database
     @RequestMapping(value = "/TaskDatabase")
     public String insertTestTask(){
         List<Task> taskList = new ArrayList<Task>();
         taskList.add(new Task(2, 010, "Sun photos", "take a picture of sunrise", 006,1000, "Production Manager", "high", "Draft","Tobias"));
         taskList.add(new Task(3, 007, "cook dinner", "cook beef with wine", 005,2000, "Service Manager", "medium", "Draft","Natalie"));
         for(Task t : taskList){
             taskRepository.save(t);

         }

         return "redirect:success";

     }

}
