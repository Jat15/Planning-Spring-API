package com.pie.planingapispring.service;

import com.pie.planingapispring.dto.*;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.jwt.InscriptionJwt;
import com.pie.planingapispring.jwt.LostPasswordJwt;
import com.pie.planingapispring.mapper.ProfileMapper;
import com.pie.planingapispring.mapper.UserMapper;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    @Autowired
    LostPasswordJwt lostPasswordJwt;
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
    public ProfileDto createUser (CreateUserDto userToCreate) {
        User user = UserMapper.fromCreateUserDtoToEntity(userToCreate);

        User userSaved = userRepository.save(user);
        if (userSaved == null) {
            return null;
        }

        //fixme trouver un moyen plus simple pour la racine
        String link = "http://localhost:4200/validate-account/"+ inscriptionJwt.generateJwtToken(user.getEmail());
        System.out.println(link);

        try {
            emailService.sendSimpleMessage(
                    user.getEmail(),
                    "Planning - Inscription",
                    "<html><body>" +
                            "<p>Bonjour ?? toi " + user.getLastName() + " " + user.getFirstName() + ",</p>" +
                            "<p>Vous ??tes les bienvenus sur notre site r??volutionnaire de Planning !</p>" +
                            "<p>Mais il manque juste une petite ??tape pour pouvoir vous ouvrir la porte de ce royaume.</p>" +
                            "<p>Il suffit de cliquer sur ce petit lien :</p>" +
                            "<p><a href='" + link + "'>" + link + "</a></p>" +
                            "<p>?? bient??t et dans l'espoir que ce royaume vous plaira.</p>" +
                            "</body></html>"
            );
        } catch (Exception e) {
            System.err.println("L'email d'inscription n'a pas ??t?? envoy??");
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

    public String lostPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }
        
        String link = "http://localhost:4200/modify-password/"+ lostPasswordJwt.generateJwtToken(user.get().getEmail());
        System.out.println(link);

        try {
            emailService.sendSimpleMessage(
                    user.get().getEmail(),
                    "Planning - Mot de passe oublier",
                    "<html><body>" +
                            "<p>Bonjour " + user.get().getLastName() + " " + user.get().getFirstName() + ",</p>" +
                            "<p>Voici le lien pour modifier votre mot de passe :</p>" +
                            "<p><a href='" + link + "'>" + link + "</a></p>" +
                            "<p>Si vous n'avez jamais demand?? ?? changer votre mot de passe, veuillez nous contacter.</p>" +
                            "<p>?? bient??t !</p>" +
                            "</body></html>"
            );
        } catch (Exception e) {
            System.err.println("L'email de changement de mot passe n'a pas ??t?? envoy??");
        }

        return "ok";
    }

    public UserDto modifyPassword(ModifyPasswordDto modifyPasswordDto) {
        if (lostPasswordJwt.validateToken(modifyPasswordDto.getToken())) {
            String email = lostPasswordJwt.getEmailFromToken(modifyPasswordDto.getToken());

            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()) { return null; }

            String hashPassword = BCrypt.hashpw( modifyPasswordDto.getPassword(), BCrypt.gensalt());
            user.get().setPassword(hashPassword);

            User saveUser = userRepository.save(user.get());
            if (saveUser == null) { return null; }

            return UserMapper.toDto(saveUser);
        }

        return  null;

    }

    public ProfileDto modifyUser(String email, ModifyUserDto userDto) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) { return null; }

        User userModify= UserMapper.userModifytoUser(userDto, user.get());

        User userSave = userRepository.save(userModify);
        if (userSave == null) { return null; }

        ProfileDto profileDto = ProfileMapper.toDto(userSave);
        return profileDto;

    }
}
