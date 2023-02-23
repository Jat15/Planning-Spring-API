package com.pie.planingapispring.repository;

import com.pie.planingapispring.entity.Rights;
import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.entity.UserPlanningId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPlanningRepository extends JpaRepository<UserPlanning, UserPlanningId> {
    List<UserPlanning> findAllByUserId(int user_id);
    Optional<UserPlanning> findUserPlanningByUserIdAndRight(int user_id, Rights right);
}
