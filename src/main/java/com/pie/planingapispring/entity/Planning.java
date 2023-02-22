package com.pie.planingapispring.entity;

import jakarta.persistence.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plannings")
public class Planning {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "planning_id")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "planning")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "planning", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPlanning> usersPlannings = new ArrayList<>();

    public Planning() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserPlanning> getUsersPlannings() {
        return usersPlannings;
    }

    public void setUsersPlannings(List<UserPlanning> usersPlannings) {
        this.usersPlannings = usersPlannings;
    }
}
