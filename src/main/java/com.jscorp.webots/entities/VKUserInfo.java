package com.jscorp.webots.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vkuserinfo")
public class VKUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "VKuid")
    private String vkKuid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private String birthdate;

   // @Column(name = "gender")
  //  private String gender;

    @Column(name = "city")
    private String city;
/*
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

 */



}
