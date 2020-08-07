package com.jscorp.webots.controllers.systemcontrollers;

import com.jscorp.webots.services.TokenService;
import com.jscorp.webots.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TestController {
    private UserServiceImpl userService;
    private TokenService tokenService;

    @Autowired
    public TestController(UserServiceImpl userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/account/autoposting")
    public ResponseEntity<?> testPost(){
        String token = tokenService.findTokenByUser(userService.findByEmail("test2@test.ru")).getToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        log.info("токен " + token);
        httpHeaders.set("Authorization", "Bearer_" + token);
        return new ResponseEntity<>("Hi!", httpHeaders, HttpStatus.OK);

    }
}
