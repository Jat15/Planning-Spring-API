package com.pie.planingapispring.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user_plannings")
public class UserPlanning {
    @EmbeddedId
    private UserPlanningId userPlanningId;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Rights rights;

    public UserPlanning() {
    }

    public UserPlanning(Integer userId, Integer planningId, Rights rights) {
        this.userPlanningId = new UserPlanningId(userId, planningId);
        this.rights = rights;
    }

    public UserPlanningId getUserPlanningId() {
        return userPlanningId;
    }

    public void setUserPlanningId(UserPlanningId userPlanningId) {
        this.userPlanningId = userPlanningId;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }
}
