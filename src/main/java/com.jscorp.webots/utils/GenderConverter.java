package com.jscorp.webots.utils;



import com.jscorp.webots.enums.GenderEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Teplykh Timofey  05.04.2020
 */
//Example 24. Enum mapping with AttributeConverter example
//http://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html
@Converter
public class GenderConverter
        implements AttributeConverter<GenderEnum, Character> {

    public Character convertToDatabaseColumn(GenderEnum value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    public GenderEnum convertToEntityAttribute(Character value) {
        if (value == null) {
            return null;
        }

        return GenderEnum.fromCode(value);
    }
}