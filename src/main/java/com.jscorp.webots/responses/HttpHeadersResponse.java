package com.jscorp.webots.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
/**
 * @author airat_f17@mail.ru
 */
@Getter
@Setter
public class HttpHeadersResponse {
    private HttpHeaders httpHeaders;
    private String token;

    public HttpHeadersResponse(HttpHeaders httpHeaders, String token) {
        this.httpHeaders = httpHeaders;
        this.token = token;
    }
}
