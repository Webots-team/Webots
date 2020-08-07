package com.jscorp.webots.repository;

import com.jscorp.webots.entities.VKUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VKUserRepository extends JpaRepository<VKUserInfo, Long> {
    VKUserInfo findOneByVkKuid(String vkKuid);
    boolean existsByVkKuid(String vkKuid);
}
