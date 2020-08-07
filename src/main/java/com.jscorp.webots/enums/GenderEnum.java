package com.jscorp.webots.enums;

/**
 * @author Teplykh Timofey  05.04.2020
 */
//Example 23. Enum with a custom constructor
//http://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html
public enum GenderEnum {
    MALE('M'),
    FEMALE('F'),
    X('X');

    private final char code;

    GenderEnum(char code) {
        this.code = code;
    }

    public static GenderEnum fromCode(char code) {
        if (code == 'M' || code == 'm' || code == 'М' || code == 'м') {
            return MALE;
        }
        if (code == 'F' || code == 'f' || code == 'Ж' || code == 'ж') {
            return FEMALE;
        }
        if (code == 'X' || code == 'x' || code == 'Х' || code == 'х') {
            return X;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

    public static GenderEnum fromCode(String code) {
        if (code.equals("M") || code.equals("m") || code.equals("М") || code.equals("м")) {
            return MALE;
        }
        if (code.equals("F") || code.equals("f") || code.equals("Ж") || code.equals("ж")) {
            return FEMALE;
        }
        if (code.equals("X") || code.equals("x") || code.equals("Х") || code.equals("х")) {
            return X;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

    public char getCode() {
        return code;
    }
}