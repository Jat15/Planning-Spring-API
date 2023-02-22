package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.PlanningDto;
import com.pie.planingapispring.entity.UserPlanning;
import org.springframework.stereotype.Service;

@Service
public class UserPlanningMapper {
    public static PlanningDto userPlanningtoPlanningDto(UserPlanning original) {
        PlanningDto resultDto = new PlanningDto();

        resultDto.setId(original.getId().getPlanningId());
        resultDto.setName(original.getPlanning().getName());

        return resultDto;
    }
}
