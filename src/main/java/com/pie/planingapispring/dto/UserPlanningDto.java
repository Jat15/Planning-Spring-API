package com.pie.planingapispring.dto;

public class UserPlanningDto {
    private UserPlanningIdDto id;
    private UserDto user;
    private PlanningDto planning;
    private String right;

    public UserPlanningDto() {
    }

    public UserPlanningIdDto getId() {
        return id;
    }

    public void setId(UserPlanningIdDto id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PlanningDto getPlanning() {
        return planning;
    }

    public void setPlanning(PlanningDto planning) {
        this.planning = planning;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
