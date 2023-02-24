package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.CreateEventDto;
import com.pie.planingapispring.dto.EventDto;
import com.pie.planingapispring.entity.Event;
import com.pie.planingapispring.entity.Planning;

import java.time.LocalDateTime;


public class EventMapper {

    public static EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setMessage(event.getMessage());
        eventDto.setStart_date(event.getStart_date());
        eventDto.setEnd_date(event.getEnd_date());

        return eventDto;
    }

    public static Event toEntity(CreateEventDto createEventDto, Integer planningId) {
        Event event = new Event();
        event.setTitle(createEventDto.getTitle());
        event.setMessage(createEventDto.getMessage());
        event.setStart_date(createEventDto.getStart_date());
        event.setEnd_date(createEventDto.getEnd_date());
        event.setPlanning(new Planning(planningId));
        event.setCreated(LocalDateTime.now());

        return event;
    }
}
