package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.CreateUserDto;
import com.pie.planingapispring.dto.ModifyUserDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.Role;
import com.pie.planingapispring.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        user.setStreet(dto.getStreet());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setZip(dto.getZip());

        String hashPassword = BCrypt.hashpw( dto.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword);

        Role role = new Role();
        role.setId(1);
        user.setRole(role);

        user.setActivate(false);

        user.setCreatedDate(LocalDateTime.now());
        return user;
    }

    public static User userModifytoUser(ModifyUserDto dto, User user) {

        if (dto.getPseudo().isPresent()) {
            user.setPseudo(dto.getPseudo().get());
        }
        if (dto.getEmail().isPresent()) {
            user.setEmail(dto.getEmail().get());
        }
        if (dto.getLastname().isPresent()) {
            user.setLastName(dto.getLastname().get());
        }
        if (dto.getFirstname().isPresent()) {
            user.setFirstName(dto.getFirstname().get());
        }
        if (dto.getAvatar().isPresent()) {
            user.setAvatar(dto.getAvatar().get());
        }
        if (dto.getBirthdate().isPresent()) {
            user.setBirthdate(dto.getBirthdate().get());
        }
        if (dto.getPhone().isPresent()) {
            user.setPhone(dto.getPhone().get());
        }
        if (dto.getStreet().isPresent()) {
            user.setStreet(dto.getStreet().get());
        }
        if (dto.getCity().isPresent()) {
            user.setCity(dto.getCity().get());
        }
        if (dto.getCountry().isPresent()) {
            user.setCountry(dto.getCountry().get());
        }
        if (dto.getZip().isPresent()) {
            user.setZip(dto.getZip().get());
        }
        if (dto.getPassword().isPresent()) {
            String hashPassword = BCrypt.hashpw(dto.getPassword().get(), BCrypt.gensalt());
            user.setPassword(hashPassword);
        }
        return user;

    }
}
