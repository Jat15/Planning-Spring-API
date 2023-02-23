package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.mapper.ProfileMapper;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

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

    public ProfileDto findProfileByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return null;
        }
        ProfileDto profileDto = ProfileMapper.toDto(user.get());
        return profileDto;
    }

    public List<UserDto> searchUsers (String textEntry) {

        List<UserDto> usersSearched = userRepository.findAllByPseudoContainingOrEmailContaining(textEntry, textEntry)
                .stream()
                .map(user -> UserMapper.toDto(user))
                .toList();

        if (usersSearched.isEmpty()) {
            return null;
        }
        return usersSearched;
    }

    public User findUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElse(null);
    }
}
