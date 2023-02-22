package com.pie.planingapispring.repository;

import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.entity.UserPlanningId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlanningRepository extends JpaRepository<UserPlanning, UserPlanningId> {
    List<UserPlanning> findAllByUserId(int user_id);

}
