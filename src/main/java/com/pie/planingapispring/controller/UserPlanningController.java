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
public class UserPlanningController {
    @Autowired
    public UserPlanningService userPlanningService;

    @GetMapping("/api/plannings")
    public ResponseEntity<List<PlanningRefactorDto>> fetchAll(Principal principal) {
        String email = principal.getName();
        List<PlanningRefactorDto> plannings = userPlanningService.all(email);

        if (plannings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(plannings);
    }
    @PostMapping("/api/userplanning")
    public ResponseEntity<UserPlanningDto> createUserPlanning(Principal principal, @RequestBody UserPlanningCreateDto dto) {
        String email = principal.getName();
        UserPlanningDto link = userPlanningService.create(email, dto);

        if (link == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(link);
    }
}
