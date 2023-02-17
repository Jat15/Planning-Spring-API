package com.pie.planingapispring.dev;


import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.pie.planingapispring.entity.User;
import com.pie.planingapispring.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {
    private final FakerService fakeDataGenerator;
    private final UserRepository userRepository;

    public DataInitializer(FakerService fakeDataGenerator, UserRepository userRepository) {
        this.fakeDataGenerator = fakeDataGenerator;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long numberUser = userRepository.count();

        if (numberUser < 5) {
            List<User> users = new ArrayList<>();

            for (int i = 0; i < 40; i++) {
                User fakeUser = fakeDataGenerator.addUser();
                users.add(fakeUser);
            }

            userRepository.saveAll(users);
        }
    }
}