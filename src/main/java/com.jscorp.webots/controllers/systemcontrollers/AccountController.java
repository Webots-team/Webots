package com.jscorp.webots.controllers.systemcontrollers;

import com.jscorp.webots.dtos.ProfileDTO;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.pub.UserPub;
import com.jscorp.webots.responses.HeaderResponse;
import com.jscorp.webots.responses.LoginResponse;
import com.jscorp.webots.responses.StatusResponseEmailExist;
import com.jscorp.webots.services.ProfileService;
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
@RequestMapping("/account")
public class AccountController {
    private UserServiceImpl userService;
    private TokenService tokenService;
    private ProfileService profileService;
    @Autowired
    public AccountController(UserServiceImpl userService, TokenService tokenService, ProfileService profileService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.profileService = profileService;
    }

    @GetMapping ("/")
    public ResponseEntity<?> account(@RequestHeader("Authorization") String token ){
        User user = tokenService.findToken(token).getUser();
        return new ResponseEntity<>(new LoginResponse(tokenService.findToken(token).getToken(), new UserPub(user)), HeaderResponse.tokenAuthorization(token), HttpStatus.OK);
    }

    @PostMapping ("/change-account-data")
    public ResponseEntity<?> changeAccountData(@Valid @RequestBody ProfileDTO profileDTO,
                                               @RequestHeader("Authorization") String token){
        User user = tokenService.findToken(token).getUser();
        log.info("Found user by id '{}'", user.getId());
        User checkEmail = userService.findByEmail(profileDTO.getEmail());
        if (checkEmail != null && !profileDTO.getEmail().equals(user.getEmail())){
            throw new StatusResponseEmailExist();
        }
        profileService.saveChanges(profileDTO, user);
        String newToken = tokenService.findTokenByUser(user).getToken();
        return new ResponseEntity<>(new LoginResponse(newToken, new UserPub(user)), HeaderResponse.tokenAuthorization(token), HttpStatus.OK);
    }
    @PostMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@RequestHeader("Authorization") String token){
        User user = tokenService.findToken(token).getUser();
        tokenService.delete(user);
        userService.delete(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
