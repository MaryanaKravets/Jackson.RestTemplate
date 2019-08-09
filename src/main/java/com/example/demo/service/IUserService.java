package com.example.demo.service;

import com.example.demo.dto.User;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public interface IUserService {

    User printDataByEmail(String email);

    HttpStatus addUser(User user) throws IOException;
}


