package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.CreateUserDto;
import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> fetchAll() {
        List<UserDto> users = userService.all();

        if(users == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<UserDto> fetchAll(@PathVariable("id") Integer id) {
        UserDto user = userService.findById(id);

        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping("/profile")
    public ResponseEntity<ProfileDto> fetchProfile(Principal principal) {
        String email = principal.getName();
        ProfileDto profile = userService.findProfileByEmail(email);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> fetchResearch(@RequestParam String researched) {
        List<UserDto> usersSearched = userService.searchUsers(researched);
        System.out.println(researched);
        if (usersSearched == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usersSearched);
    }

    @PostMapping("/create_user")
    public ResponseEntity<ProfileDto> addUser (@RequestBody CreateUserDto dto) {
        ProfileDto userCreated = userService.createUser(dto);

        if (userCreated != null) {
            return ResponseEntity
                    .ok(userCreated);
        } else {
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
