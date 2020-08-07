package com.jscorp.webots.responses;

import org.springframework.http.HttpHeaders;
/**
 * @author airat_f17@mail.ru
 */
public class HeaderResponse {
    public static HttpHeaders tokenAuthorization(String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer_" + token);
        return httpHeaders;
    }
}
