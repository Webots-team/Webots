package com.jscorp.webots.services;

import com.jscorp.webots.entities.Token;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author airat_f17@mail.ru
 */
@Slf4j
@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private EntityManager entityManager;

    @Autowired
    public TokenService(TokenRepository tokenRepository, EntityManager entityManager) {
        this.tokenRepository = tokenRepository;
        this.entityManager = entityManager;
    }

    public Token findToken(String token){
        StringBuilder stringBuilder = new StringBuilder(token);
        String res = stringBuilder.substring(7);
        return tokenRepository.findByToken(res);
    }
    public Token findTokenByUser(User user){
        return tokenRepository.findTokenByUser(user);
    }

    @Transactional
    public Token saveToken(String token, User user){
        Session session = entityManager.unwrap(Session.class);
        Token tokenExist = tokenRepository.findTokenByUser(user);
        if (tokenExist == null){
            Token newToken = new Token();
            newToken.setToken(token);
            newToken.setUser(user);
            session.save(newToken);
            log.info("New token created '{}' for user by id '{}'",token,user.getId());
            return newToken;
        }
        else {
            tokenExist.setToken(token);
            tokenExist.setUser(user);
            session.saveOrUpdate(tokenExist);
            log.info("New token updated '{}' for user by id '{}'",token,user.getId());
            return tokenExist;
        }
    }
    @Transactional
    public void delete (User user){
        Session session = entityManager.unwrap(Session.class);
        Token tokenForDelete = tokenRepository.findTokenByUser(user);
        session.delete(tokenForDelete);
    }
}
