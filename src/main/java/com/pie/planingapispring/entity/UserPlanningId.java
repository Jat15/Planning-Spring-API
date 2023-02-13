package com.pie.planingapispring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserPlanningId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "planing_id",nullable = false)
    private Integer planingId;

    public UserPlanningId() {
    }
    
    public UserPlanningId(Integer userId, Integer planingId) {
        this.userId = userId;
        this.planingId = planingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlaningId() {
        return planingId;
    }

    public void setPlaningId(Integer planingId) {
        this.planingId = planingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlanningId that = (UserPlanningId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(planingId, that.planingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, planingId);
    }
}
