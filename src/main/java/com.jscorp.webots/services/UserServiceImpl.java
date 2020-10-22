package com.jscorp.webots.services;


import com.jscorp.webots.dtos.UserAuthenticationDTO;

import com.jscorp.webots.entities.Location;
import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.Role;
import com.jscorp.webots.entities.Token;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.repository.RoleRepository;
import com.jscorp.webots.repository.UserRepository;
import com.jscorp.webots.utils.UTCProvider;
import com.jscorp.webots.utils.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author airat_f17@mail.ru
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UTCProvider UTCProvider;
    private SocialLinkService socialLinkService;
    private EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setSocialLinkService(SocialLinkService socialLinkService) {
        this.socialLinkService = socialLinkService;
    }

    @Autowired
    public void setUTCProvider(UTCProvider UTCProvider) {
        this.UTCProvider = UTCProvider;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByPhone(String phone) {
        return userRepository.findOneByPhone(phone);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public User findByFirstName(String firstName){
        return userRepository.findByfirstname(firstName);

    }
    @Override
    public User findByEmail(String email){
        return userRepository.findOneByEmail(email);

    }

    @Override
    public User findByToken(Token token) {
        return userRepository.findByToken(token);
    }

    @Override
    public boolean validPassword(User user, String hashPassword) {
        return passwordEncoder.matches(hashPassword, user.getPassword());
    }
    @Override
    public List<User> getAll(){
        List <User> list = userRepository.findAll();
        log.info("{} users found", list.size());
        return list;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user = findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    @Transactional
    public User save(SystemUser systemUser) {
        User user = new User();

        user.setUsername(systemUser.getUsername());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setPhone(systemUser.getPhone());
        user.setFirstname(systemUser.getFirstname());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());

        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }
    @Transactional
    public User save(UserAuthenticationDTO userAuthenticationDTO) {
        Session session = entityManager.unwrap(Session.class);

        User user = new User();
        Profile profile = new Profile();
        Location location = new Location();

        profile.setSocialLinks(socialLinkService.addAllNetworks(profile));
        Role role = roleRepository.findOneByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setFirstname(userAuthenticationDTO.getFirstname());
        user.setPassword(passwordEncoder.encode(userAuthenticationDTO.getPassword()));
        user.setEmail(userAuthenticationDTO.getEmail());
        user.setUsername(userAuthenticationDTO.getFirstname());
        profile.setFirstname(user.getFirstname());
        profile.setUTC(UTCProvider.getUtc());
        profile.setEmail(userAuthenticationDTO.getEmail());
        LocalDate birthdate = LocalDate.now();
        profile.setBirthdate(birthdate);
        profile.setLocation(location);
        profile.setUser(user);
        user.setProfile(profile);
        log.info("User was saved by email: '{}'", user.getEmail());
        session.saveOrUpdate(user);
        return user;
    }
    @Transactional
    public void delete(User user){
        Session session = entityManager.unwrap(Session.class);
        session.delete(user);
        log.info("User with id '{}' deleted", user.getId());
    }






}
