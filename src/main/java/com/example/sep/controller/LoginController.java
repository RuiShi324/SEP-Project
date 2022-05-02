package com.example.sep.controller;

import com.example.sep.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//for login and logout action
//@Controller return a html page
//@RestController return a string
@Controller
public class LoginController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //direct to the main page(from login page)

    @RequestMapping("/mainPage")
    public String mainPage(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("userId") != null) {
            // System.out.println(session.getAttribute("userId"));
            return "mainPage";

        } else {
            // System.out.println("session unbound");
            return "redirect:/";
        }
    }

    // if login success, set the session
    public static void setSession(HttpSession session, String userName, List<Employee> em){
        // setsession
        session.setAttribute("userName", userName);
        session.setAttribute("role", em.get(0).getRole());
        session.setAttribute("userId", em.get(0).getId());
        session.setAttribute("department", em.get(0).getDepartment());


        /*
            employees.add(new Employee( 001,"Janet", "password", 32, 1, "Senior Customer Service manager" ,"senior customer service manager","1997.05.15"));
        employees.add(new Employee( 002,"Sarah", "password", 40, 1, "Customer Service manager"  ,"customer service officer", "1997.05.15"));
        employees.add(new Employee( 003,"Alice", "password", 31, 1, "Financial Manager"  ,"financial manager","1997.05.15"));
        employees.add(new Employee( 004,"Mike", "password", 51, 0, "Administration Manager" ,"admin manager","1997.05.15"));
        employees.add(new Employee( 005,"Kate", "password", 40, 1, "Service Manager" ,"service manager","1997.05.15"));
        employees.add(new Employee( 006,"Jack", "password", 32, 0, "Production Manager" ,"production manager","1997.05.15"));
        employees.add(new Employee( 007,"Natalie", "password", 28, 1,"Service  Subteam"  ,"top chef","1997.05.15"));
        employees.add(new Employee( 010,"Tobias", "password", 32, 0,"Production  Subteam" ,"photographer","1997.05.15"));
        employees.add(new Employee( 011,"Simon", "password", 36, 0,"HR Manager","HR manager", "1997.05.15"));*/
        // event management is only visible to customer service officer/ senior customer service manager/
        // financial manager/ admin manager
/*        if (em.get(0).getRole().equals("financial manager")
                | em.get(0).getRole().equals("admin manager")
                | em.get(0).getRole().equals("senior customer service manager")
                | em.get(0).getRole().equals("customer service officer")) {
            session.setAttribute("event", "event");

        } else {
            session.setAttribute("event", "hidden");
        }

        // task management is only visible to production and service department managers and staff
        if (em.get(0).getDepartment().equals("Production Manager")
                | em.get(0).getDepartment().equals("Service Manger")) {

            session.setAttribute("task", "task");

        } else {
            session.setAttribute("task", "hidden");
        }

        // staff management is only visible to HR manager/ production manager/ service manager
        if (em.get(0).getRole().equals("HR manager") 
                | em.get(0).getRole().equals("production manager")
                | em.get(0).getRole().equals("service manager")) {

            session.setAttribute("staff", "staff");

        } else {
            session.setAttribute("staff", "hidden");
        }

        // Financial management is only visible to Financial manager/ production manager/ financial manager
        if (em.get(0).getRole().equals("financial manager") | em.get(0).getRole().equals("production manager")
                | em.get(0).getRole().equals("service manager")) {
            session.setAttribute("finance", "finance");

        } else {
            session.setAttribute("finance", "hidden");
        }*/


        if (em.get(0).getId() == 003
                | em.get(0).getId() == 004
                | em.get(0).getId() == 001
                | em.get(0).getId() == 002) {
            session.setAttribute("event", "event");

        } else {
            session.setAttribute("event", "hidden");
        }

        // task management is only visible to production and service department managers and staff
        if (em.get(0).getId() == 005
                | em.get(0).getId() == 006
                | em.get(0).getId() == 007
                | em.get(0).getId() == 010
        ) {

            session.setAttribute("task", "task");

        } else {
            session.setAttribute("task", "hidden");
        }

        // staff management is only visible to HR manager/ production manager/ service manager
        if (em.get(0).getId() == 011
                | em.get(0).getId() == 005
                | em.get(0).getId() == 006) {

            session.setAttribute("staff", "staff");

        } else {
            session.setAttribute("staff", "hidden");
        }

        // Financial management is only visible to Financial manager/ production manager/ financial manager
        if (em.get(0).getId() == 003
                | em.get(0).getId() == 005
                | em.get(0).getId() == 006) {
            session.setAttribute("finance", "finance");

        } else {
            session.setAttribute("finance", "hidden");
        }
    }

    //get the login param after user submits the login page
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpSession session, Model model) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password"); // password hash


        List<Employee> em = employeeRepository.findByName(userName);


        if (em.get(0).getPassword().equals(password)) {
            setSession(session, userName, em);

            return "redirect:mainPage";
        } else {

            return "redirect:loginFailure";

        }

    }


    //direct to the login page
    @RequestMapping("/")
    public String html() {
        return "index";
    }

    //direct to the login page when logging out
    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }

    //direct to the login failure page
    @RequestMapping("/loginFailure")
    public String success(Map<String, Object> map) {
        map.put("hello", "The login fails: please check the password");

        return "loginFailure";
    }


    //for insert a few data into the database
    @RequestMapping(value = "/data")
    public String data() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee( 001,"Janet", "password", 32, 1, "Senior Customer Service manager" ,"senior customer service manager","1997.05.15"));
        employees.add(new Employee( 002,"Sarah", "password", 40, 1, "Customer Service manager"  ,"customer service officer", "1997.05.15"));
        employees.add(new Employee( 003,"Alice", "password", 31, 1, "Financial Manager"  ,"financial manager","1997.05.15"));
        employees.add(new Employee( 004,"Mike", "password", 51, 0, "Administration Manager" ,"admin manager","1997.05.15"));
        employees.add(new Employee( 005,"Kate", "password", 40, 1, "Service Manager" ,"service manager","1997.05.15"));
        employees.add(new Employee( 006,"Jack", "password", 32, 0, "Production Manager" ,"production manager","1997.05.15"));
        employees.add(new Employee( 007,"Natalie", "password", 28, 1,"Service Manager"  ,"top chef","1997.05.15"));
        employees.add(new Employee( 010,"Tobias", "password", 32, 0,"Production Manager" ,"photographer","1997.05.15"));
        employees.add(new Employee( 011,"Simon", "password", 36, 0,"HR Manager","HR manager", "1997.05.15"));
        for (Employee emp : employees) {
            employeeRepository.save(emp);

        }

        return "redirect:success";

    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

}



