package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.CreateUserDto;
import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
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

    //Fixme Gérée en patch ou put ou post avec angular
    @GetMapping("/validate/{token}")
    public ResponseEntity<UserDto> activateUser(@PathVariable String token) {
        UserDto userActivate = userService.activateUser(token);

        if (userActivate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userActivate);
    }

    @PostMapping("/lostpassword")
    public ResponseEntity<?> lostPassword(@RequestParam String email) {
        String userActivate = userService.lostPassword(email);

        if (userActivate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/modifypassword")
    public ResponseEntity<UserDto> modifyPassword(@RequestParam String token, @RequestParam String password) {
        UserDto userNewPassword = userService.modifyPassword(token, password);

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
