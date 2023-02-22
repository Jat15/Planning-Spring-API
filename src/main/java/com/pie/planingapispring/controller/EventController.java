package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.EventDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> fetchById(@PathVariable("id") Integer id) {
        EventDto event = eventService.findById(id);

        if(event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> fetchAll() {

        List<EventDto> events = eventService.findAll();

        if(events == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(events);
    }
}
