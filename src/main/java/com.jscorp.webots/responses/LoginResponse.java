package com.jscorp.webots.responses;

import com.jscorp.webots.pub.UserPub;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "Login response",
        description="Тело ответа в формате JSON, содержит token и информацию о пользователе.")
public class LoginResponse {
    @ApiModelProperty(value = "Token", position = 0)
    private String token;

    @ApiModelProperty(value = "User data", position = 1)
    private UserPub user;
}