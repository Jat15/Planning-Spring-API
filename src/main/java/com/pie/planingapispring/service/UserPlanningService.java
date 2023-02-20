package com.pie.planingapispring.service;

import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.entity.UserPlanningId;
import com.pie.planingapispring.repository.UserPlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPlanningService {

    @Autowired
    private UserPlanningRepository userPlanningRepository;

    public Optional<UserPlanning> findById(Integer userSessionID, Integer planningId) {
        return userPlanningRepository.findById(new UserPlanningId(userSessionID, planningId));
    }
}
