package com.jscorp.webots.utils;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
/**
 * @author airat_f17@mail.ru
 */
@Component
public class UTCProvider {
    public String getUtc() {
        String utc = DateTime.now().toDateTime().toString();
        StringBuilder stringBuilder = new StringBuilder(utc);
        utc = stringBuilder.substring(utc.length()-6);
        return utc;

    }
}
