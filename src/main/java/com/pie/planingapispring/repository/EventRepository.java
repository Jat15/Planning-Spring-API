package com.pie.planingapispring.repository;

import com.pie.planingapispring.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventsByPlanningId(Integer planningId);
}
