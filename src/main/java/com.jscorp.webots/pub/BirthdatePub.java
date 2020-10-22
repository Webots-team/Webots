package com.jscorp.webots.pub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BirthdatePub {
//    private LocalDate birthdate;
    @ApiModelProperty(position = 1)
    private int day;
    @ApiModelProperty(position = 2)
    private int month;
    @ApiModelProperty(position = 3)
    private int year;

    public BirthdatePub(LocalDate birthdate) {
//        birthdate = birthdate;
        this.day = birthdate.getDayOfMonth();
        this.month = birthdate.getMonthValue();
        this.year = birthdate.getYear();
    }
}
