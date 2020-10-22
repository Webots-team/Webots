package com.jscorp.webots.pub;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jscorp.webots.entities.User;
import com.jscorp.webots.enums.GenderEnum;
import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Teplykh Timofey  08.04.2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User profile",
        description="Профиль пользователя.")
public class UserProfilePub {

    @ApiModelProperty(position = 0)
    private Long userId;

    @NotEmpty
    @ApiModelProperty(value = "Фамилия. Size(min = 2, max = 50)", position = 1)
    private String lastname;

    @NotEmpty
    @ApiModelProperty(value = "Имя. Size(min = 2, max = 50)", position = 2)
    private String firstname;

    @NotEmpty
    @ApiModelProperty(value = "Отчество. Size(min = 2, max = 50)", position = 3)
    private String patronymic;

    @ApiModelProperty(value = "Дата рождения", position = 4)
    private BirthdatePub birthdate;
//    private LocalDate birthdate;

    @ApiModelProperty(value = "User gender",
            allowableValues = "M - MALE, F - FEMALE, X - Xgender",
            example = "M",
            position = 5)
    private GenderEnum gender;

    @ApiModelProperty(position = 6)
    private String country;

    @ApiModelProperty(position = 7)
    private String city;

    @ApiModelProperty(position = 8)
    private String phone;
    @ApiModelProperty(position = 9)
    private String email;

    @JsonProperty("social_links")
    @ApiModelProperty(position = 10)
    private Set<SocialLinkPub> socialLinks;

    @ApiModelProperty(position = 12)
    private byte[] photo;
    @ApiModelProperty(position = 13)
    private String UTC;

    @JsonProperty("about_me")
    @ApiModelProperty(position = 15)
    private String aboutMe;

    public UserProfilePub(@NotNull User user) {
        this.userId = user.getId();
        this.lastname = user.getProfile().getLastname();
        this.firstname = user.getProfile().getFirstname();
        this.patronymic = user.getProfile().getPatronymic();
        this.email = user.getProfile().getEmail();
        this.birthdate = new BirthdatePub(user.getProfile().getBirthdate());
//        this.birthdate = user.getProfile().getBirthdate();
        this.gender = user.getProfile().getGender();
        this.country = user.getProfile().getLocation() == null ? null :
                user.getProfile().getLocation().getCountry();
        this.city = user.getProfile().getLocation() == null ? null :
                user.getProfile().getLocation().getCity();
        this.phone = user.getProfile().getPhone();
        this.UTC = user.getProfile().getUTC();
        this.socialLinks = Collections.isEmpty(user.getProfile().getSocialLinks()) ? null :
                user.getProfile().getSocialLinks()
                        .stream()
                        .map(SocialLinkPub::new)
                        .collect(Collectors.toSet());
        this.photo = user.getProfile().getPhoto();
    }

    public UserProfilePub(@NotNull User user,
                          @NotEmpty String lastname, @NotEmpty String firstname, @NotEmpty String patronymic) {
        this(user);
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class WorkSchedulePub {
        private LocalTime startTime;
        private LocalTime endTime;
        private ZoneOffset zoneOffset;
    }
}