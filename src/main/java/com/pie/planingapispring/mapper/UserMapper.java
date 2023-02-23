package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.CreateUserDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.Role;
import com.pie.planingapispring.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setPseudo(user.getPseudo());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }

    public static User fromCreateUserDtoToEntity(CreateUserDto dto) {
        User user = new User();

        user.setPseudo(dto.getPseudo());
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastname());
        user.setFirstName(dto.getFirstname());
        user.setAvatar(dto.getAvatar());
        user.setBirthdate(dto.getBirthdate());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());

        user.setStreet(dto.getStreet());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setZip(dto.getZip());

        Role role = new Role();
        role.setId(1);
        user.setRole(role);
        user.setActivate(false);
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }
}
