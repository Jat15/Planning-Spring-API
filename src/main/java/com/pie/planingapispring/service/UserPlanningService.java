package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.*;
import com.pie.planingapispring.entity.*;
import com.pie.planingapispring.mapper.PlanningMapper;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.mapper.UserPlanningIdMapper;
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

    public List<PlanningRefactorDto> all(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) { return null; }

        List<UserPlanning> plannings = userPlanningRepository.findAllByUserId(user.get().getId());

        if (plannings.isEmpty()) { return null; }

        List<PlanningRefactorDto> planningRefactorDtos = plannings.stream()
                .map(item -> UserPlanningMapper.userPlanningtoPlanningDto(item))
                .toList();

        return planningRefactorDtos;
    }

    public UserPlanningDto create(String email, UserPlanningCreateDto dto) {
        if ("MAIN".equals(dto.getRight())) { return null; }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }

        Optional<UserPlanning> planning = userPlanningRepository.findUserPlanningByUserIdAndRight(user.get().getId(), Rights.MAIN);
        if (planning.isEmpty()) { return null; }


        Optional<User> userLink = userRepository.findById(dto.getUserId());
        if (userLink.isEmpty()) { return null; }

        UserPlanning userPlanning = new UserPlanning(
                userLink.get().getId(),
                planning.get().getPlanning().getId(),
                Rights.valueOf(dto.getRight())
        );

        UserPlanning result = userPlanningRepository.save(userPlanning);
        if (result == null) { return null; }

        PlanningDto planningDto = PlanningMapper.toDto(planning.get().getPlanning());
        UserDto userDto = UserMapper.toDto(userLink.get());
        UserPlanningIdDto userPlanningIdDto = UserPlanningIdMapper.toDto(result.getId());

        UserPlanningDto userPlanningDto = new UserPlanningDto();
        userPlanningDto.setPlanning(planningDto);
        userPlanningDto.setUser(userDto);
        userPlanningDto.setId(userPlanningIdDto);
        userPlanningDto.setRight(result.getRight().name());

        return userPlanningDto;
    }
}
