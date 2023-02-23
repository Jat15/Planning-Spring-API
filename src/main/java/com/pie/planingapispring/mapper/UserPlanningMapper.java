package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.PlanningRefactorDto;
import com.pie.planingapispring.entity.UserPlanning;
import org.springframework.stereotype.Service;

@Service
public class UserPlanningMapper {
    public static PlanningRefactorDto userPlanningtoPlanningDto(UserPlanning original) {
        PlanningRefactorDto resultDto = new PlanningRefactorDto();

        resultDto.setId(original.getId().getPlanningId());
        resultDto.setName(original.getPlanning().getName());
        resultDto.setRight(original.getRight().name());

        return resultDto;
    }
}
