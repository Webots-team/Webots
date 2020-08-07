package com.jscorp.webots.exception;

/**
 * @author Teplykh Timofey  05.04.2020
 */
public final class UserExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public UserExistException() {
        super();
    }

    public UserExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserExistException(final String message) {
        super(message);
    }

    public UserExistException(final Throwable cause) {
        super(cause);
    }

}
