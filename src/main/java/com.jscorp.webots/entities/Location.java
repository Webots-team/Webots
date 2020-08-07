package com.jscorp.webots.entities;

import com.jscorp.webots.pub.LocationPub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Entity
@Validated
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "locations")
@EntityListeners({AuditingEntityListener.class})
public class Location extends AbstractAuditable<Profile, Long> {

    @NotNull
    @Column(name = "country", length = 50)
    private String country;

    @NotNull
    @Column(name = "city",length = 50)
    private String city;

    @Nullable
    @Column(name = "street",length = 50)
    private String street;

    @Nullable
    @Column(name = "post_code", length = 10)
    @Size(min = 6, max = 10)
    private String postCode;

    @OneToOne(cascade = CascadeType.MERGE) //optional = false,
    @JoinColumn(name = "profile_id",
            referencedColumnName = "id")//nullable = false
    private Profile profile; //владелец

    public Location(@NotNull LocationPub locationPub){
        this.country = locationPub.getCountry();
        this.city = locationPub.getCity();
    }

    public Location editLocation(@NotNull LocationPub locationPub){
        this.country = locationPub.getCountry();
        this.city = locationPub.getCity();
        return this;
    }

//    @Override
//    public String toString() {
//        return "Location{" +
//                "'country':'" + country + "'" +
//                "'city':'" + city + "'" +
//                "'street':'" + street + "'" +
//                "'postCode':'" + postCode + "'" +
//                '}';
//    }
}
