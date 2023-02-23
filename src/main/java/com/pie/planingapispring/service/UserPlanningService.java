package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.*;
import com.pie.planingapispring.entity.*;
import com.pie.planingapispring.mapper.PlanningMapper;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.mapper.UserPlanningIdMapper;
import com.pie.planingapispring.repository.PlanningRepository;
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
    @Autowired
    private PlanningRepository planningRepository;
    @Autowired
    EmailServiceImpl emailService;

    public Optional<UserPlanning> findById(Integer userSessionID, Integer planningId) {
        return userPlanningRepository.findById(new UserPlanningId(userSessionID, planningId));
    }

    public List<UserPlanningDto> myPlannings(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }
        List<UserPlanning> plannings = userPlanningRepository.findAllByUserId(user.get().getId());

        UserPlanning  createMain = null;
        if (plannings.isEmpty()) {
            createMain = createMain(user.get());
        } else {
            List<UserPlanning> filterByMain = plannings
                    .stream()
                    .filter(item -> item.getRight().equals(Rights.MAIN)).toList();

            if (filterByMain.isEmpty()) {
                createMain = createMain(user.get());
            }
        }
        if (createMain != null) {
            plannings.add(createMain);
        }
        if (plannings == null) { return null; }

        List<UserPlanningDto> planningRefactorDtos = plannings.stream()
                .map(item -> {
                    UserPlanningDto userPlanningDto = new UserPlanningDto();
                    userPlanningDto.setPlanning(PlanningMapper.toDto(item.getPlanning()));
                    userPlanningDto.setUser(UserMapper.toDto(item.getUser()));
                    userPlanningDto.setId(UserPlanningIdMapper.toDto(item.getId()));
                    userPlanningDto.setRight(item.getRight().name());

                    return userPlanningDto;
                })
                .toList();

        return planningRefactorDtos;
    }

    public UserPlanning createMain(User user) {
        //Création d'un Planning MAIN si il n'existe pas
        Planning planning = new Planning();
        planning.setName("Planning of " + user.getPseudo());

        planning = planningRepository.save(planning);
        if (planning == null) { return null; }

        UserPlanningId newUserPlanningId = new UserPlanningId(user.getId(), planning.getId());
        UserPlanning newUserPlanning = new UserPlanning();
        newUserPlanning.setId(newUserPlanningId);
        newUserPlanning.setRight(Rights.MAIN);

        UserPlanning saveUserPlanning = userPlanningRepository.save(newUserPlanning);
        if (saveUserPlanning == null) { return null; }
        saveUserPlanning.setUser(user);
        saveUserPlanning.setPlanning(planning);

        return  saveUserPlanning;
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

        try {
            emailService.sendSimpleMessage(
                    userLink.get().getEmail(),
                    "Planning - " + user.get().getPseudo() + " vous partage son planning",
                    "<html><body>" +
                            "<p>Bonjour " + userLink.get().getFirstName() + " " + userLink.get().getLastName() + ",</p>" +
                            "<p>L'utilisateur " + user.get().getPseudo() + " partage son planning avec vous.</p>" +
                            "<p>Ce planning se nomme : " + planning.get().getPlanning().getName() + ".</p>" +
                            "<p>Vous avez le droit : " + dto.getRight() + ".</p>" +
                            "<p>Bonne journée à vous.</p>" +
                            "</body></html>"

            );
        } catch (Exception e) {
            System.err.println("L'email d'inscription n'a pas été envoyé");
        }

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

    public List<UserPlanningDto> planningShared(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }

        Optional<UserPlanning> planningMain = userPlanningRepository.findUserPlanningByUserIdAndRight(user.get().getId(), Rights.MAIN);
        if (planningMain.isEmpty()) { return null; }

        List<UserPlanning> planningsShared = userPlanningRepository.findUserPlanningsByPlanningId(planningMain.get().getPlanning().getId());
        if (planningsShared.isEmpty()) { return null; }

        List<UserPlanningDto> planningRefactorDtos = planningsShared.stream()
                .map(item -> {
                    UserPlanningDto userPlanningDto = new UserPlanningDto();
                    userPlanningDto.setPlanning(PlanningMapper.toDto(item.getPlanning()));
                    userPlanningDto.setUser(UserMapper.toDto(item.getUser()));
                    userPlanningDto.setId(UserPlanningIdMapper.toDto(item.getId()));
                    userPlanningDto.setRight(item.getRight().name());

                    return userPlanningDto;
                })
                .toList();

        return planningRefactorDtos;
    }
    public String delete(String email,Integer idUser){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }

        Optional<UserPlanning> planningMain = userPlanningRepository.findUserPlanningByUserIdAndRight(user.get().getId(), Rights.MAIN);
        if (planningMain.isEmpty()) { return null; }

        UserPlanningId id = new UserPlanningId(idUser, planningMain.get().getId().getPlanningId());

        Optional<UserPlanning> userPlanning = userPlanningRepository.findById(id);
        if (userPlanning.isEmpty()) { return null; }
        if ("MAIN".equals(userPlanning.get().getRight().name())) { return null; }

        userPlanningRepository.deleteById(id);

        return "ok";
    }
}
