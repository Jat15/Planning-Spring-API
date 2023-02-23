package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.*;
import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.service.UserPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/userplanning")
public class UserPlanningController {
    @Autowired
    public UserPlanningService userPlanningService;

    @GetMapping("/myplannings")
    public ResponseEntity<List<UserPlanningDto>> fetchAllMyPlannings(Principal principal) {
        String email = principal.getName();
        List<UserPlanningDto> plannings = userPlanningService.myPlannings(email);

        if (plannings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(plannings);
    }
    @PostMapping
    public ResponseEntity<UserPlanningDto> createUserPlanning(Principal principal, @RequestBody UserPlanningCreateDto dto) {
        String email = principal.getName();
        UserPlanningDto link = userPlanningService.create(email, dto);

        if (link == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(link);
    }
    @GetMapping("/planningsshared")
    public ResponseEntity<List<UserPlanningDto>> fetchPlanningsShared(Principal principal) {
        String email = principal.getName();
        List<UserPlanningDto> planningsShared = userPlanningService.planningShared(email);

        if (planningsShared == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(planningsShared);
    }
}
