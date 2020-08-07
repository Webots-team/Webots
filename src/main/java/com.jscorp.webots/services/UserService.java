package com.jscorp.webots.services;

import com.jscorp.webots.dtos.UserAuthenticationDTO;
import com.jscorp.webots.entities.Token;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.utils.SystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author natalya_ezhkova@mail.ru
 */

public interface UserService extends UserDetailsService {
    User findByPhone(String phone);
    User findOneByUsername(String username);
    User findById(Long id);
    boolean isUserExist(String phone);
//    User save(UserAuthenticationDTO userAuthenticationDTO);
    User save(SystemUser systemUser);
    User findByFirstName(String firstName);
    User findByEmail(String email);
    User findByToken(Token token);

    List<User> getAll();
    boolean validPassword(User user, String hashPassword);
}
