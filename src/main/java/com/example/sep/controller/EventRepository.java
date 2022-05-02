package com.example.sep.controller;

import com.example.sep.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//for connecting to the database
public interface EventRepository extends JpaRepository<Event, Integer> {
    public List<Event> findByClientName(String clientName);

}

