package com.jscorp.webots.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Entity
@Validated
@Data
@EqualsAndHashCode(callSuper = true, of = {"link"})
@NoArgsConstructor
@Table(name = "social_links")
@EntityListeners({AuditingEntityListener.class})
public class SocialLink extends AbstractAuditable<User, Long> {

//    @Column(nullable = false)
    private String link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "social_network_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_social_link_social_network_id"))
    private SocialNetwork socialNetwork;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_social_links_profile_id"))
    private Profile profile;

    public SocialLink(String link, SocialNetwork socialNetwork, Profile profile) {
        this.link = link;
        this.socialNetwork = socialNetwork;
        this.profile = profile;
    }
    public SocialLink(SocialNetwork socialNetwork){
        this.socialNetwork = socialNetwork;
    }

//    @Override
//    public String toString() {
//        return "Social link{" +
//                "'name':'" + socialNetwork.getName() + "'" +
//                "'link':'" + link + "'" +
//                '}';
//    }

}