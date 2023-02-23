package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.EventDto;
import com.pie.planingapispring.entity.Event;
import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.mapper.EventMapper;
import com.pie.planingapispring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private UserPlanningService userPlanningService;

    @Autowired
    private EventRepository eventRepository;

    public EventDto findById(Integer id) {
        Integer userSessionID = 1;
        Optional<Event> eventOpt = eventRepository.findById(id);

        if (eventOpt.isEmpty()) {
            return null;
        }
        Event event = eventOpt.get();
        Optional<UserPlanning> userPlanningOpt = userPlanningService.findById(userSessionID, event.getPlanning().getId());
        if(userPlanningOpt.isEmpty()){
            return null;
        }

        return EventMapper.toDto(event);
    }

    public List<EventDto> findAllByPlanningId(Integer userId, Integer planningId) {
        Optional<UserPlanning> userPlanningOpt = userPlanningService.findById(userId, planningId);
        if(userPlanningOpt.isEmpty()){
            return null;
        }
        List<Event> events = eventRepository.findEventsByPlanningId(planningId);
        if (events.isEmpty()){
            return null;
        }
        return events.stream().map(EventMapper::toDto).toList();
    }
}
