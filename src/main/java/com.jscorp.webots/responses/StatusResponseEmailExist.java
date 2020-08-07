package com.jscorp.webots.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author airat_f17@mail.ru
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "user with this email is already exist")
public class StatusResponseEmailExist extends RuntimeException {


}
