package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.UserPlanningIdDto;
import com.pie.planingapispring.entity.UserPlanningId;
import org.springframework.stereotype.Service;

@Service
public class UserPlanningIdMapper {
    public static UserPlanningIdDto toDto(UserPlanningId original) {
        UserPlanningIdDto dto = new UserPlanningIdDto();
        dto.setPlanningId(original.getPlanningId());
        dto.setUserId(original.getUserId());

        return dto;
    }
}
