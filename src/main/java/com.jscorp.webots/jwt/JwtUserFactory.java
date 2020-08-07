package com.jscorp.webots.jwt;

import com.jscorp.webots.entities.Role;
import com.jscorp.webots.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public final class JwtUserFactory {
    public JwtUserFactory(){

    }
    public static JwtUser create(User user){
        return new JwtUser(user.getId(),user.getUsername(),user.getFirstname(),user.getLastName(),user.getPassword(),user.getEmail(),user.isEnabled(),mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRole){
        return userRole.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }


}
