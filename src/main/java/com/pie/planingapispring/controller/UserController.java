package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.*;
import com.pie.planingapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200/")
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> fetchAll(@PathVariable("id") Integer id) {
        UserDto user = userService.findById(id);

        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> fetchProfile(Principal principal) {
        String email = principal.getName();
        ProfileDto profile = userService.findProfileByEmail(email);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/search/{researched}")
    public ResponseEntity<List<UserDto>> fetchResearch(@PathVariable String researched) {
        List<UserDto> usersSearched = userService.searchUsers(researched);

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

    @PatchMapping("/validate")
    public ResponseEntity<UserDto> activateUser(@RequestBody String token) {
        UserDto userActivate = userService.activateUser(token);

        if (userActivate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userActivate);
    }

    @PostMapping("/lostpassword")
    public ResponseEntity<?> lostPassword(@RequestBody String email) {
        String userActivate = userService.lostPassword(email);

        if (userActivate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/modifypassword")
    public ResponseEntity<UserDto> modifyPassword(@RequestBody ModifyPasswordDto modifyPasswordDto) {
        UserDto userNewPassword = userService.modifyPassword(modifyPasswordDto);

        if (userNewPassword == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userNewPassword);
    }

    @PatchMapping("/modifyuser")
    public ResponseEntity<ProfileDto> modifyUser(Principal principal,@RequestBody ModifyUserDto user) {
        String email = principal.getName();
        ProfileDto userNewPassword = userService.modifyUser(email, user);

        if (userNewPassword == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userNewPassword);
    }

}
