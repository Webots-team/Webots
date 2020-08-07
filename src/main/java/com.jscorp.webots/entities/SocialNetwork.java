package com.jscorp.webots.entities;

import com.jscorp.webots.pub.SocialLinkPub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Entity
@Validated
@Data
@EqualsAndHashCode(callSuper = true, of = {"name"})
@NoArgsConstructor
@Table(name = "social_networks")
@EntityListeners({AuditingEntityListener.class})
public class SocialNetwork extends AbstractAuditable<User, Long> {

    @NotNull
    @Size(min = 2, max = 30)
    //@Pattern(regexp = "[^0-9]*")
    @Column(nullable = false, unique = true)
    private String name;

    //TODO проверка на URL
    @Column(nullable = false)
    private String url; //ссылка на главную страницу сайта соцсети

    @Column
    private String icon;

    public SocialNetwork(@NotNull @Size(min = 2, max = 30) String name, String url, String icon) {
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public SocialNetwork(@NotNull SocialLinkPub socialLinkPub){
        this.name = socialLinkPub.getName();
        this.url = socialLinkPub.getLink();
        this.icon = socialLinkPub.getIcon();
    }

    @Override
    public String toString() {
        return "Social network{" +
                "'name':'" + name + "'" +
                "'url':'" + url + "'" +
                '}';
    }

}