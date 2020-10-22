package com.jscorp.webots.services;

import com.jscorp.webots.dtos.ProfileDTO;
import com.jscorp.webots.dtos.SocialLinksDTO;
import com.jscorp.webots.entities.Location;
import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.enums.GenderEnum;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author airat_f17@mail.ru
 */
@Slf4j
@Service
public class ProfileService {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public void saveChanges(ProfileDTO profileDTO, User user){
        Session session = entityManager.unwrap(Session.class);
        Profile profile = user.getProfile();
        if (!profileDTO.getLastname().equals("")){
            profile.setLastname(profileDTO.getLastname());
        }
        if (!profileDTO.getFirstname().equals("")){
            user.setUsername(profileDTO.getFirstname());
            profile.setFirstname(profileDTO.getFirstname());
        }
        if (!profileDTO.getPatronymic().equals("")){
            profile.setPatronymic(profileDTO.getPatronymic());
        }
        if (!profileDTO.getEmail().equals("")){
            profile.setEmail(profileDTO.getEmail());
            user.setEmail(profileDTO.getEmail());
        }
        if (profileDTO.getBirthdate() != null){
            setBirthdateFromDTO(profileDTO,profile);
            log.info("bithdate is {}", profileDTO.getBirthdate());
        }
        if (profileDTO.getGender() != ' '){
            profile.setGender(GenderEnum.fromCode(profileDTO.getGender()));
        }
        if (profileDTO.getCity() != null | profileDTO.getStreet() != null){
            Location location = profile.getLocation();
            if (profileDTO.getCity() != null){
                location.setCity(profileDTO.getCity());
            }
            if (profileDTO.getStreet() != null){
                location.setCity(profileDTO.getCity());
            }
            profile.setLocation(location);
        }
        if (profileDTO.getPhone() != null){
            profile.setPhone(profileDTO.getPhone());
        }
        if (!Collections.isEmpty(profileDTO.getSocialLinks())){
            setSocialLinksFromDTO(profileDTO,profile);

        }

        session.update(user);
        session.update(profile);

    }
    private void setBirthdateFromDTO(ProfileDTO profileDTO, Profile profile){
        LocalDate bithdateFromProfile = profile.getBirthdate() == null ? LocalDate.of(1900,1,1) : profile.getBirthdate();
        int newDay = bithdateFromProfile.getDayOfMonth();
        int newMoth = bithdateFromProfile.getMonthValue();
        int newYear = bithdateFromProfile.getYear();
        for (Map.Entry<String,Integer> entry : profileDTO.getBirthdate().entrySet()){
            if (entry.getKey().equals("day") && entry.getValue() != null){
                newDay = entry.getValue();
            }
            if (entry.getKey().equals("month") && entry.getValue() != null){
                newMoth = entry.getValue();
            }
            if (entry.getKey().equals("year") && entry.getValue() != null){
                newYear = entry.getValue();
            }

        }
        bithdateFromProfile = LocalDate.of(newYear,newMoth,newDay);
        profile.setBirthdate(bithdateFromProfile);
    }

    private void setSocialLinksFromDTO(ProfileDTO profileDTO,Profile profile){
        for (SocialLinksDTO socialLinks : profileDTO.getSocialLinks()){
            if (socialLinks.getName().equals("VK") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("VK")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("Instagram") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("Instagram")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("Facebook") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("Facebook")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("YouTube") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("YouTube")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("Twitter") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("Twitter")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("OK") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("OK")).forEach(l -> l.setLink(socialLinks.getLink()));
            }
            if (socialLinks.getName().equals("Skype") && socialLinks.getLink() != null){
                profile.getSocialLinks().stream().filter(l -> l.getSocialNetwork()
                        .getName()
                        .equals("Skype")).forEach(l -> l.setLink(socialLinks.getLink()));
            }

        }

    }

}
