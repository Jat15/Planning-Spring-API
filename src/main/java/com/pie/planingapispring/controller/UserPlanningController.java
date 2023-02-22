package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.PlanningDto;
import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.Planning;
import com.pie.planingapispring.entity.UserPlanning;
import com.pie.planingapispring.service.UserPlanningService;
import com.pie.planingapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserPlanningController {
    @Autowired
    public UserPlanningService userPlanningService;

    @GetMapping("/api/plannings")
    public ResponseEntity<List<PlanningDto>> fetchAll(Principal principal) {
        String email = principal.getName();
        List<PlanningDto> plannings = userPlanningService.all(email);

        if (plannings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(plannings);
    }
}
