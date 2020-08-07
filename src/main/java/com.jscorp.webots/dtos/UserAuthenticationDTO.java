package com.jscorp.webots.dtos;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author airat_f17@mail.ru
 */
@Data
public class UserAuthenticationDTO {

    @NotNull
    @Size(min = 4, max = 15)
    private String password;

    @NotNull
    @Size(min = 2, max = 15)
    private String firstname;

    @NotNull
    @Size(min = 4, max = 15)
    @Email
    private String email;

}
