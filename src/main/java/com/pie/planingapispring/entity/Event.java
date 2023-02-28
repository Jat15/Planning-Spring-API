package com.pie.planingapispring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Table(name="events")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="event_id")
    private Integer id;
    @Column(nullable = false)
    private String title;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime start_date;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime end_date;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date",columnDefinition = "timestamp default current_timestamp", updatable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name="planning_id")
    private Planning planning;

    public Event() {
    }

    public Event(Integer id, String title, String message, LocalDateTime start_date, LocalDateTime end_date, LocalDateTime created, Planning planning) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created = created;
        this.planning = planning;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }
}
