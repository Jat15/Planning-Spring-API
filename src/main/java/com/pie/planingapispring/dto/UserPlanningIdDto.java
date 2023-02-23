package com.pie.planingapispring.dto;

public class UserPlanningIdDto {
    private Integer userId;
    private Integer planningId;

    public UserPlanningIdDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlanningId() {
        return planningId;
    }

    public void setPlanningId(Integer planningId) {
        this.planningId = planningId;
    }
}
