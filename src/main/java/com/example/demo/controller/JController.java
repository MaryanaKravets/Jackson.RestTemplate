package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class JController {
    private IUserService iUserService;

    @ResponseBody
    @GetMapping(value = "")
    public User printDataByEmail(@RequestParam
                                         String email) {
        return iUserService.printDataByEmail(email);
    }

    @ResponseBody
    @PostMapping(value = "/add")
    public HttpStatus addUser(@RequestBody User user
    ) throws IOException {
        return iUserService.addUser(user);
    }
}



