package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setPseudo(user.getPseudo());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }
}
