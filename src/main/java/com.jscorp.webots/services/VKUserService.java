package com.jscorp.webots.services;

import com.jscorp.webots.entities.VKUserInfo;
import com.jscorp.webots.repository.VKUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Service
public class VKUserService {
    private VKUserRepository vkUserRepository;

    @Autowired
    public void setVkUserRepository(VKUserRepository vkUserRepository) {
        this.vkUserRepository = vkUserRepository;
    }

    public VKUserInfo save(VKUserInfo vkUser) {
        return vkUserRepository.save(vkUser);
    }

    public VKUserInfo findById(Long id) {
        return vkUserRepository.findById(id).get();
    }

    public VKUserInfo findByVkUid(String vkKuid) {
        return vkUserRepository.findOneByVkKuid(vkKuid);
    }

    public boolean isUserExist(String vkKuid) {
        return vkUserRepository.existsByVkKuid(vkKuid);
    }

}
