package com.pie.planingapispring.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class PlanningDto {
    private Integer id;
    private String name;

    public PlanningDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
