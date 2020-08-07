package com.jscorp.webots.pub;

import com.jscorp.webots.entities.Profile;
import com.jscorp.webots.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Teplykh Timofey  08.04.2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User",
        description="Данные пользователя, в т.ч. его профиль и роль.")
public class UserPub {

    @ApiModelProperty(position = 0)
    private Long id;

    @NotEmpty
    @Size(min = 6, max = 32)
    @ApiModelProperty(example = "petr@mail.ru", position = 1)
    private String email;

    @ApiModelProperty(position = 2)
    private boolean confirmed_email;

    @ApiModelProperty(position = 3)
    private boolean authorized;

    @Nullable
    @ApiModelProperty(position = 5)
    private UserProfilePub profile;

    public UserPub(@NotNull User user) {
        this(user, false);
    }

    public UserPub(@NotNull User user, boolean withoutProfile) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.confirmed_email = withoutProfile;
        this.authorized = withoutProfile;
        this.profile = withoutProfile? null : new UserProfilePub(user);
    }

    @Override
    public String toString() {
        return "User{" +
                "'id':'" + id + "'" +
                ", 'email':'" + email + "'" +
                ", 'confirm_email':" + confirmed_email +
                ", " + profile +
                '}';
    }

}