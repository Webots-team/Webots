package com.jscorp.webots.services;

import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.SocialLink;
import com.jscorp.webots.repository.SocialLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class SocialLinkService {
    private SocialLinkRepository socialLinkRepository;
    private UserServiceImpl userService;
    private SocialNetworkService socialNetworkService;

    @Autowired
    public void setSocialNetworkService(SocialNetworkService socialNetworkService) {
        this.socialNetworkService = socialNetworkService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSocialLinkRepository(SocialLinkRepository socialLinkRepository) {
        this.socialLinkRepository = socialLinkRepository;
    }

    public Set<SocialLink> addAllNetworks(Profile profile){
        SocialLink VK = new SocialLink(socialNetworkService.findByName("VK"));
        VK.setProfile(profile);
        SocialLink instagram = new SocialLink(socialNetworkService.findByName("Instagram"));
        instagram.setProfile(profile);
        SocialLink FB = new SocialLink(socialNetworkService.findByName("Facebook"));
        FB.setProfile(profile);
        SocialLink skype = new SocialLink(socialNetworkService.findByName("Skype"));
        skype.setProfile(profile);
        SocialLink youTube = new SocialLink(socialNetworkService.findByName("YouTube"));
        youTube.setProfile(profile);
        SocialLink twitter = new SocialLink(socialNetworkService.findByName("Twitter"));
        twitter.setProfile(profile);
        SocialLink OK = new SocialLink(socialNetworkService.findByName("OK"));
        OK.setProfile(profile);
        Set <SocialLink> socialLinks = new HashSet<>(Arrays.asList(VK,instagram,FB,skype,youTube,twitter,OK));
//        socialLinks.add(VK);
//        socialLinks.add(instagram);
//        socialLinks.add(FB);
//        socialLinks.add(skype);
//        socialLinks.add(youTube);
//        socialLinks.add(twitter);
//        socialLinks.add(OK);
        log.info("Social links: " + socialLinks);
        return socialLinks;


    }

}
