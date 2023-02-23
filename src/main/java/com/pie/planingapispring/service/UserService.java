package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.CreateUserDto;
import com.pie.planingapispring.dto.ProfileDto;
import com.pie.planingapispring.dto.UserDto;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.jwt.InscriptionJwt;
import com.pie.planingapispring.mapper.ProfileMapper;
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
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    InscriptionJwt inscriptionJwt;
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

    public ProfileDto createUser (CreateUserDto userToCreate) {
        User user = UserMapper.fromCreateUserDtoToEntity(userToCreate);
        User userSaved = userRepository.save(user);
        if (userSaved == null) {
            return null;
        }

        //fixme trouver un moyen plus simple pour la racine
        String link = "http://localhost:8080"+"/api/users/validate/"+ inscriptionJwt.generateJwtToken(user.getEmail());

        try {
            emailService.sendSimpleMessage(
                    user.getEmail(),
                    "Planning - Inscription",
                    "<html><body>" +
                            "<p>Bonjour à toi " + user.getLastName() + " " + user.getFirstName() + ",</p>" +
                            "<p>Vous êtes les bienvenus sur notre site révolutionnaire de Planning !</p>" +
                            "<p>Mais il manque juste une petite étape pour pouvoir vous ouvrir la porte de ce royaume.</p>" +
                            "<p>Il suffit de cliquer sur ce petit lien :</p>" +
                            "<p><a href='" + link + "'>" + link + "</a></p>" +
                            "<p>À bientôt et dans l'espoir que ce royaume vous plaira.</p>" +
                            "</body></html>"
            );
        } catch (Exception e) {
            System.err.println("L'email d'inscription n'a pas été envoyé");
        }

        return ProfileMapper.toDto(userSaved);
    }

    public UserDto activateUser(String token) {
        if (inscriptionJwt.validateToken(token)) {
            String email = inscriptionJwt.getEmailFromToken(token);

            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()) { return null; }

            user.get().setActivate(true);

            User saveUser = userRepository.save(user.get());
            if (saveUser == null) { return null; }

            return UserMapper.toDto(saveUser);
        }

        return  null;
    }
}
