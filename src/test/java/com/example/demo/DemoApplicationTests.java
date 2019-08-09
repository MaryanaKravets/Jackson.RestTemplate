package com.example.demo;

import com.example.demo.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8080";

    @Test
    public void testGetRequest() {
        User user = new User("Olena", "Lisova", LocalDate.of(2019, 8, 2), 1234, "olena1995@gmail.com",
                Map.of("hw1", true, "hw2", false, "hw3", true));
        String url = URL + "/?email=olena1995@gmail.com";
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url, User.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user.getName(), Objects.requireNonNull(responseEntity.getBody()).getName());
        assertEquals(user.getSurName(), responseEntity.getBody().getSurName());
        assertEquals(user.getLastLoginDate(), responseEntity.getBody().getLastLoginDate());
        assertEquals(user.getHomeworkToIsDone(), responseEntity.getBody().getHomeworkToIsDone());
    }


    @Test
    public void testPostRequest() {
        User user = new User("Iryna", "Antonyak", "iryna1984@ukr.net");
        String url = URL + "/add";
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity(url, httpEntity, HttpStatus.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseEntity.getBody());
    }
}
