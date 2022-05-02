package com.example.sep.controller;


import com.example.sep.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

//event controller class
@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    //check the role of user and direct to the event management page
    @RequestMapping("/eventManagement")
    public String eventManagement(HttpServletRequest request, Map<String, Object> map) {
        List<Event> eventList = eventRepository.findAll();
//        System.out.println(eventList);
        map.put("eventlist", eventList);

        HttpSession session = request.getSession();
        // get current role
        String role = (String) session.getAttribute("role");
        if(role.equals("customer service officer")){
            map.put("createView", "createView");
        }else{
            map.put("createView", "hidden");
        }

        return "eventManagement";
    }


    //direct to the edit event page and check the role of user
    //different roles have different actions
    @PostMapping(value = "/editEvent")
    public String editEvent(HttpServletRequest request ,@RequestParam("eventId") String eventId, Map<String, Object> map){

        // System.out.println(eventId);

        Event event = eventRepository.findById(parseInt(eventId)).get();

        map.put("event", event);



        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // default view setting
        session.setAttribute("budgetCommentView", "disabled");
        session.setAttribute("choiceView", "disabled");

        // change the view depending on role and status in edit page
        // if the status is closed, then no one can edit it any longer
        if(!event.getStatus().equals("Closed")){
            // if the status is not closed, then AM and SCSM can edit "reject" or "approve" choice
            // only when AM
            switch(role){
                case "senior customer service manager":
                    if(event.getStatus().equals("Created")){
                        session.setAttribute("budgetCommentView", "disabled");
                        session.setAttribute("choiceView", "choice");
                    }
                    break;
                case "admin manager":
                    if(event.getStatus().equals("FMcommented")){
                        session.setAttribute("budgetCommentView", "disabled");
                        session.setAttribute("choiceView", "choice");
                    }
                    break;

                case "financial manager":
                    if(event.getStatus().equals("SCSapproved")) {
                        session.setAttribute("budgetCommentView", "budgetComment");
                        session.setAttribute("choiceView", "disabled");
                    }
                    break;
                default:
                    session.setAttribute("budgetCommentView", "disabled");
                    session.setAttribute("choiceView", "disabled");


            }
        }


        return "editEventRequest";

    }

    //direct to the create event page
    @RequestMapping("/createEvent")
    public String createEventPage(){
        return "createEventRequest";        // go to createEventRequest.html
    }

    //get values from the edit event page
    @PostMapping(value = "/editEventRequest")
    public String editEvent(HttpServletRequest request, @RequestParam("eventId") String eventId, @RequestParam("comment") String comment, @RequestParam("expectedBudget") int expectedBudget,
                            @RequestParam("choice") String choice){
        // System.out.println(eventId);
        HttpSession session = request.getSession();

        // get current role
        String role = (String) session.getAttribute("role");
        // get the event currently dealt with
        Event event = eventRepository.findById(parseInt(eventId)).get();

        if(choice.equals("reject") || event.getStatus().equals("Closed")){
            event.setStatus("Closed");
        }else{
            switch (role) {
                case "admin manager":
                    if(event.getStatus().equals("FMcommented")){
                        event.setStatus("AMapproved");

                    }

                    break;
                case "senior customer service manager":
                    if(event.getStatus().equals("Created")){
                        event.setStatus("SCSapproved");

                    }

                    break;
                case "financial manager":
                    event.setComment(comment);
                    event.setStatus("FMcommented");
                    break;


            }
        }
        eventRepository.save(event);



        return "redirect:/eventManagement";


    }




    //get value from the create event page
    @PostMapping(value = "/createEventRequest")
    public String createEvent(@RequestParam("clientName") String clientName, @RequestParam("eventType") String eventType,
                              @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
                              @RequestParam("preferences") String preferences, @RequestParam("expectedBudget") int expectedBudget,
                              @RequestParam("description") String description,
                              Map<String, Object> map) {
        
                               
        Event event = new Event(clientName, eventType, beginDate, endDate, preferences, expectedBudget, description);

        eventRepository.save(event);

        return "redirect:/eventManagement";     // must use redirect here

    }

}
