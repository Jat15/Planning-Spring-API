package com.pie.planingapispring.dto;

public class PlanningDto {
    private Integer id;
    private String name;
    private String right;

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
    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
