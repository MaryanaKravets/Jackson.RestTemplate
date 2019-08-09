package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.dto.User;
import com.example.demo.exception.NotFoundExceptions;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;

@Service
public class UserService implements IUserService {
    private List<User> userList = new ArrayList<>(Arrays.asList(
            new User("Olena", "Lisova", LocalDate.of(2019, 8, 2), 1234, "olena1995@gmail.com",
                    Map.of("hw1", true, "hw2", false, "hw3", true)),
            new User("Oksana", "Bynkovska", LocalDate.of(2019, 7, 23), 8994, "oksana342@gmail.com",
                    Map.of("hw1", true, "hw2", true, "hw3", true)),
            new User("Taras", "Glova", LocalDate.of(2019, 6, 29), 6785, "taras1985@dmail.com",
                    Map.of("hw1", false, "hw2", false, "hw3", true))
    ));
    private static final String FILE_PATH = "target/file.json";


    @Override
    public User printDataByEmail(String email) {
        return userList.stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny().orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public HttpStatus addUser(User user) {
        Random random = new Random();
        int generatedId = (random.nextInt(20) + 1) * user.getName().length() * user.getSurName().length();
        int maxDay = (int) LocalDate.of(2019, 8, 8).toEpochDay();
        long randomDay = maxDay - random.nextInt(730);
        User user1 = new User(user.getName(), user.getSurName(),
                LocalDate.ofEpochDay(randomDay),
                generatedId, user.getEmail(), Collections.emptyMap());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(FILE_PATH), user1);
            return HttpStatus.CREATED;
        } catch (IOException e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}


