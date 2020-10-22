package com.jscorp.webots.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.jscorp.webots.entities.SocialLink;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author airat_f17@mail.ru
 */
@Data
public class ProfileDTO {

    @NotNull
    @Size(min = 4, max = 15)
    private String password;

    @NotNull
    @Size(min = 2, max = 15)
    private String firstname;
    @NotNull
    @Size(min = 4, max =  15)
    private String lastname;

    @NotNull
    @Size(min = 4, max = 15)
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 15)
    private String patronymic; //отчество patronymic

    @NotNull
    @JsonProperty("birthdate")
    private Map<String,Integer> birthdate;

    private char gender;

    private String city;
    private String street;

    @NotNull
    private String phone;

    private String UTC;
    @NotNull
    @JsonProperty("social_links")
    private List<SocialLinksDTO> socialLinks;

    private byte[] photo;

}
