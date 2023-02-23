package com.pie.planingapispring.dto;

public class UserPlanningCreateDto {
    private String right;
    private Integer userId;

    public UserPlanningCreateDto() {
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
