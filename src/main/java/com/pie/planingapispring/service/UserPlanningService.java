package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.PlanningDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.Planning;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.entity.UserPlanningId;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.mapper.UserPlanningMapper;
import com.pie.planingapispring.repository.UserPlanningRepository;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPlanningService {

    @Autowired
    private UserPlanningRepository userPlanningRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserPlanning> findById(Integer userSessionID, Integer planningId) {
        return userPlanningRepository.findById(new UserPlanningId(userSessionID, planningId));
    }

    public List<PlanningDto> all(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) { return null; }

        List<UserPlanning> plannings = userPlanningRepository.findAllByUserId(user.get().getId());

        if (plannings.isEmpty()) { return null; }

        List<PlanningDto> planningDtos = plannings.stream()
                .map(item -> UserPlanningMapper.userPlanningtoPlanningDto(item))
                .toList();

        return planningDtos;
    }
}
