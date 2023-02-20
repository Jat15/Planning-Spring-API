package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.EventDto;
import com.pie.planingapispring.entity.Event;


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
}
