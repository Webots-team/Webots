package com.jscorp.webots.jwt;

import com.jscorp.webots.entities.User;
import com.jscorp.webots.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findOneByUsername(username);
        User user = userService.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("User not founded");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("Successfully loaded");

        return jwtUser;
    }

//    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
////        User user = userService.findOneByUsername(username);
//        User user = userService.findById(id);
//        if (user == null){
//            throw new UsernameNotFoundException("User not founded");
//        }
//        JwtUser jwtUser = JwtUserFactory.create(user);
//        log.info("Successfully loaded");
//

//        return jwtUser;
//    }

}
