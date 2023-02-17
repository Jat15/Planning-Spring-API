package com.pie.planingapispring.controller;

import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.repository.UserRepository;
import com.pie.planingapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
