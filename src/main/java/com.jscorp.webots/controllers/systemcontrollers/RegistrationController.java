package com.jscorp.webots.controllers.systemcontrollers;

import com.jscorp.webots.dtos.UserAuthenticationDTO;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.jwt.JwtTokenProvider;
import com.jscorp.webots.pub.UserPub;
import com.jscorp.webots.responses.HeaderResponse;
import com.jscorp.webots.responses.LoginResponse;
import com.jscorp.webots.responses.StatusResponseEmailExist;
import com.jscorp.webots.services.TokenService;
import com.jscorp.webots.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airat_f17@mail.ru
 */
/*
поменять меппинг
/registration
/process
 */
@Slf4j
@RestController
@RequestMapping ("/api/auth")
public class RegistrationController {
    private UserServiceImpl userService;
    private JwtTokenProvider jwtTokenProvider;
    private TokenService tokenService;

    @Autowired
    public RegistrationController(UserServiceImpl userService, JwtTokenProvider jwtTokenProvider, TokenService tokenService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrationProcess(@Valid @RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        User exist = userService.findByEmail(userAuthenticationDTO.getEmail());
        if (exist != null) {
            log.error("User with email '{}' is already exist", userAuthenticationDTO.getEmail());
            throw new StatusResponseEmailExist();
        }
        User user = userService.save(userAuthenticationDTO);
        String token = jwtTokenProvider.createToken(user.getId(),user.getRoles());
        user.setToken(tokenService.saveToken(token,user));
        return new ResponseEntity<>(new LoginResponse(token,new UserPub(user)), HeaderResponse.tokenAuthorization(token), HttpStatus.OK);
    }
}
