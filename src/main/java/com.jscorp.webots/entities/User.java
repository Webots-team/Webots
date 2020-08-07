package com.jscorp.webots.entities;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name = "fullprivileges",
            nullable=false,
            columnDefinition = "boolean default false")
    private boolean fullPrivileges = false;

    @Column(nullable=false,
            columnDefinition = "boolean default true")
    private boolean enabled = true;// false;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "tokens_id", referencedColumnName = "id")
    private Token token;


    @OneToOne(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id",
            referencedColumnName = "id")
    private Profile profile;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


}