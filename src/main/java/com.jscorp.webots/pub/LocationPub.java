package com.jscorp.webots.pub;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.jscorp.webots.entities.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author Teplykh Timofey  05.04.2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "Location")
@ApiModel(value = "Location",
        description="Место нахождения.")
public class LocationPub {

    @Nullable
    @ApiModelProperty(value = "Страна")
    private String country;

    @Nullable
    @ApiModelProperty(value = "Город")
    private String city;

    public LocationPub(@NotNull Location location) {
        this.country = location.getCountry();
        this.city = location.getCity();
    }

    @Override
    public String toString() {
        return "Location{" +
                "'country':'" + country + "'" +
                "'city':'" + city + "'" +
                '}';
    }

}