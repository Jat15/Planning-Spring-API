package com.pie.planingapispring.dev;


import com.pie.planingapispring.entity.Role;
import com.pie.planingapispring.entity.User;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class FakerService {
    public User addUser() {

        Faker faker = new Faker();

        User newUser = new User();

        newUser.setPseudo(faker.name().username());

        String email = faker.internet().emailAddress();
        newUser.setEmail(email);
        newUser.setLastName(faker.name().lastName());
        newUser.setFirstName(faker.name().firstName());
        newUser.setAvatar(faker.avatar().image());
        newUser.setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());
        newUser.setPhone(faker.phoneNumber().cellPhone());

        String password = faker.internet().password();
        String hashPassword = BCrypt.hashpw( password, BCrypt.gensalt());
        newUser.setPassword(hashPassword);

        newUser.setCreatedDate(LocalDateTime.now());

        newUser.setStreet(faker.address().streetAddress());
        newUser.setCity(faker.address().city());
        newUser.setCountry(faker.address().country());
        newUser.setZip(faker.address().zipCode());

        double nActivate =  Math.random();
        if (nActivate < 0.8f) {
            newUser.setActivate(true);
        } else {
            newUser.setActivate(false);
        }

        double nRole =  Math.random();
        Role role = new Role();
        if (nRole < 0.9f){
           role.setId(1);
        } else {
            role.setId(2);
        }
        newUser.setRole(role);

        System.out.println("Role :");
        System.out.println(newUser.getRole().getId());
        System.out.println("Activate :");
        System.out.println(newUser.isActivate());
        System.out.println("Email :");
        System.out.println(email);
        System.out.println("Password");
        System.out.println(password);
        System.out.println("------------------------------------------------");

        return newUser;
    }
}
