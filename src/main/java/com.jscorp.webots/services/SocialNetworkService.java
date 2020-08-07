package com.jscorp.webots.services;

import com.jscorp.webots.entities.SocialNetwork;
import com.jscorp.webots.repository.SocialNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
/**
 * @author airat_f17@mail.ru
 */
@Service
public class SocialNetworkService {
    private SocialNetworkRepository socialNetworkRepository;
    @Autowired
    public void setSocialNetworkRepository(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public SocialNetwork findByName(String name){
        return socialNetworkRepository.findByName(name);
    }
    public SocialNetwork findById(Long id){
        return socialNetworkRepository.findById(id).get();
    }
    public List<SocialNetwork> findAll(){
        return socialNetworkRepository.findAll();
    }



}
