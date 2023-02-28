package com.pie.planingapispring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserPlanningId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "planning_id")
    private Integer planningId;

    public UserPlanningId() {
    }
    
    public UserPlanningId(Integer userId, Integer planningId) {
        this.userId = userId;
        this.planningId = planningId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlanningId that = (UserPlanningId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(planningId, that.planningId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, planningId);
    }
}
