package com.pie.planingapispring.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoricalModify {

    @Id
    @Column(name="history_modify_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime modifyDate;
    @Enumerated(EnumType.ORDINAL)
    private Rights modifyType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

}
