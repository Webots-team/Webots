package com.jscorp.webots.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author airat_f17@mail.ru
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "wrong password")
public class StatusResponseWrongPassword extends RuntimeException {


}
