package com.jscorp.webots.exception;

/**
 * @author Teplykh Timofey  05.04.2020
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        super("could not find user '" + id + "'.");
    }
}
