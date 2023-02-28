package com.pie.planingapispring.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users_plannings")
public class UserPlanning {
    @EmbeddedId
    private UserPlanningId id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("planning_id")
    @JoinColumn(name = "planning_id")
    private  Planning planning;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="user_planing_right", nullable = false)
    private Rights right;

    public UserPlanning() {
    }

    public UserPlanning(Integer userId, Integer planningId, Rights right) {
        this.id = new UserPlanningId(userId, planningId);
        this.right = right;
    }

    public UserPlanningId getId() {
        return id;
    }

    public void setId(UserPlanningId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Rights getRight() {
        return right;
    }

    public void setRight(Rights right) {
        this.right = right;
    }
}
