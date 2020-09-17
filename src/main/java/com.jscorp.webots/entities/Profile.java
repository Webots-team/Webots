package com.jscorp.webots.entities;

import com.jscorp.webots.enums.GenderEnum;
import com.jscorp.webots.utils.GenderConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Entity
@Validated
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "profiles")
public class Profile extends AbstractAuditable<User, Long> {

//    @OneToOne(mappedBy = "profile", cascade = CascadeType.REFRESH)//, cascade = CascadeType.REFRESH
//    @JsonIgnore
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[^0-9]*")
    @Column( length = 50)
    private String lastname;

    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[^0-9]*")
    @Column( length = 50)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[^0-9]*")
    @Column( length = 50)
    private String patronymic;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private LocalDate birthdate;

    //Exmpl.24 - http://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html
    @Convert(converter = GenderConverter.class)
    @ColumnDefault("'X'")
    @Column(name = "gender", length = 1)//, nullable = false)
    public GenderEnum gender = GenderEnum.X;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) //optional = false,
    @JoinColumn(name = "location_id",
            referencedColumnName = "id") //nullable = false
    private Location location;  //страна, город

    @Column(length = 15)
    @Pattern(regexp = "^\\+?(\\d{11,15})$")//, message = "Enter phone in the following format: +7xxxxxxxxxx")
    private String phone;
    @Column (name = "email")
    @NotNull
    private String email;

    @Column(name = "UTC",length = 50)
    private String UTC;

    @OneToMany(mappedBy = "profile",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<SocialLink> socialLinks;


    @Column
    private byte[] photo;


    public Profile(Long id) {
        this.setId(id);
    }

    public Profile(String lastname, String firstname, String patronymic) {
        super();
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
    }

    public Profile(String lastname, String firstname, String patronymic,String email, LocalDate birthdate, GenderEnum gender) {
        super();
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public void addSocialLink(SocialLink socialLink){
        if (!this.socialLinks.contains(socialLink)) {
            this.socialLinks.add(socialLink);
            socialLink.setProfile(this);
        }
    }

    public void removeSocialLink(SocialLink socialLink){
        if (this.socialLinks.contains(socialLink)) {
            this.socialLinks.remove(socialLink);
            socialLink.setProfile(null);
        }
    }

    @NotNull
    @Nullable
    public String toStringFIO() { //Иванов И.И.
        return String.format("%s%s %s.%s.", lastname.substring(0, 1).toUpperCase(), lastname.substring(1),
                firstname.substring(0, 1).toUpperCase(), patronymic.substring(0, 1).toUpperCase());
    }

    @NotNull
    @Nullable
    public String toStringFullFIO() {//Иванов Иван Иванович
        return String.format("%1$s %2$s %3$s", lastname, firstname, patronymic);
    }


}
