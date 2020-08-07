package com.jscorp.webots.pub;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.jscorp.webots.entities.SocialLink;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "Social link")
@ApiModel(value = "Social link",
        description="Ссылка на аккаунт социальной сеть пользователя..")
public class SocialLinkPub {

    @NotNull
    @NotEmpty
    @ApiModelProperty(value = "Name social network",
            position = 0,
            required = true)
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(position = 1)
    private String link;

    @Nullable
    @Column
    @ApiModelProperty(position = 2)
    private String icon;

    public SocialLinkPub(@NotNull SocialLink socialLink) {
        this.name = socialLink.getSocialNetwork().getName();
        this.link = socialLink.getLink();
        this.icon = socialLink.getSocialNetwork().getIcon();
    }
    @Override
    public String toString() {
        return "SocialLinkPub{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}