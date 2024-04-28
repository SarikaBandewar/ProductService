package com.sk.productservice.commons;

import com.sk.productservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    private final RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(
                "http://localhost:3030/users/validate/" + token,
                null,
                UserDto.class);

        if (responseEntity.getBody() == null) {
            return null;
        }
        return responseEntity.getBody();
    }
}
