package com.jscorp.webots.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@Getter
@Setter
public class JwtUser implements UserDetails {
    private final Long id;
    private final String userName;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;
    private final boolean enable;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String userName, String firstname, String lastname, String password, String email, boolean enable, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.enable = enable;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}
