package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.CreateEventDto;
import com.pie.planingapispring.dto.EventDto;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.service.EventService;
import com.pie.planingapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @GetMapping("/events/{id}")
    public ResponseEntity<EventDto> fetchById(@PathVariable("id") Integer id, Principal userSession) {
        User user = userService.findUserByEmail(userSession.getName());
        EventDto event = eventService.findById(id, user.getId());

        if(event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @GetMapping("/planning/{id}/events")
    public ResponseEntity<List<EventDto>> fetchAllByPlanningId(@PathVariable("id") Integer planningId, Principal userSession) {
        User user = userService.findUserByEmail(userSession.getName());
        List<EventDto> events = eventService.findAllByPlanningId(user.getId(), planningId);
        if(events == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

    @PostMapping("/planning/{id}/events")
    public ResponseEntity<EventDto> createEvent(@RequestBody CreateEventDto createEventDto,
                                                @PathVariable("id") Integer planningId,
                                                Principal userSession){
        User user = userService.findUserByEmail(userSession.getName());
        EventDto eventDto = eventService.createEvent(createEventDto, planningId, user.getId());
        if(eventDto == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventDto);
    }

    @PutMapping("/planning/{id}/events/{event_id}")
    public ResponseEntity<EventDto> createEvent(@RequestBody CreateEventDto createEventDto,
                                                @PathVariable("id") Integer planningId,
                                                @PathVariable("event_id") Integer eventId,
                                                Principal userSession){
        User user = userService.findUserByEmail(userSession.getName());
        EventDto eventDto = eventService.EditEvent(createEventDto, planningId, eventId, user.getId());
        if(eventDto == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventDto);
    }
}
