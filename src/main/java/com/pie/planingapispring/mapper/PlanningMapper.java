package com.pie.planingapispring.mapper;


import com.pie.planingapispring.dto.PlanningDto;
import com.pie.planingapispring.entity.Planning;
import org.springframework.stereotype.Service;

@Service
public class PlanningMapper {
    public static PlanningDto toDto(Planning original) {
        PlanningDto dto = new PlanningDto();
        dto.setId(original.getId());
        dto.setName(original.getName());

        return dto;
    }
}
