package com.jscorp.webots.controllers.systemcontrollers;


import com.jscorp.webots.dtos.UserAuthenticationDTO;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.jwt.JwtTokenProvider;
import com.jscorp.webots.pub.UserPub;
import com.jscorp.webots.responses.HeaderResponse;
import com.jscorp.webots.responses.LoginResponse;
import com.jscorp.webots.responses.StatusResponseEmailExist;
import com.jscorp.webots.responses.StatusResponseWrongPassword;

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

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private UserServiceImpl userService;
    private JwtTokenProvider jwtTokenProvider;
    private TokenService tokenService;

    @Autowired
    public AuthorizationController(UserServiceImpl userService, JwtTokenProvider jwtTokenProvider, TokenService tokenService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
    }

    @PostMapping (path = "/login", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> login(@Valid @RequestBody UserAuthenticationDTO userAuthenticationDTO){
        User user = userService.findByEmail(userAuthenticationDTO.getEmail());
        if (user == null){
            log.error("User with email '{}' is not exist", userAuthenticationDTO.getEmail());
            throw new StatusResponseEmailExist();
        }
        else if (!userService.validPassword(user, userAuthenticationDTO.getPassword())){
            log.error("Wrong password");
            throw new StatusResponseWrongPassword();
        }
        log.info("Founded user by id '{}'", user.getId());

        String token = jwtTokenProvider.createToken(user.getId(),user.getRoles());
        tokenService.saveToken(token,user);
        return new ResponseEntity<>(new LoginResponse(token,new UserPub(user)), HeaderResponse.tokenAuthorization(token), HttpStatus.OK);



    }


}
