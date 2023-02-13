package com.pie.planingapispring.entity;

import jakarta.persistence.*;

@Entity
public class Planning {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "planning_id")
    private Integer id;

    // relation vers UserPlanning et vers events



    public Planning() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
