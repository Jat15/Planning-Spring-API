package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<UserDto> all() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()){
            return null;
        }

        List<UserDto> usersDto = users.stream()
                .map(user -> UserMapper.toDto(user))
                .toList();

        return usersDto;
    }

    public UserDto findById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return null;
        }

        UserDto userDto = UserMapper.toDto(user.get());

        return userDto;
    }
}
