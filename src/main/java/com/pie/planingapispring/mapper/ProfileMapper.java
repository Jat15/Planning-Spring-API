package com.pie.planingapispring.mapper;

import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.entity.User;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper {
    public static ProfileDto toDto (User user) {
        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(user.getId());
        profileDto.setPseudo(user.getPseudo());
        profileDto.setEmail(user.getEmail());
        profileDto.setLastname(user.getLastName());
        profileDto.setFirstname(user.getFirstName());
        profileDto.setAvatar(user.getAvatar());
        profileDto.setBirthdate(user.getBirthdate());
        profileDto.setPhone(user.getPhone());
        profileDto.setCreatedDate(user.getCreatedDate());
        profileDto.setModifyDate(user.getModifyDate());
        profileDto.setStreet(user.getStreet());
        profileDto.setCity(user.getCity());
        profileDto.setCountry(user.getCountry());
        profileDto.setZip(user.getZip());

        return profileDto;
    }
}
