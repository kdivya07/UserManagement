package com.usermanagement.constants;

public final class ErrorConstants {

    public static final String USER_NOT_FOUND = "User with ID %d not found.";
    public static final String EMAIL_ALREADY_EXISTS = "Email %s already exists.";

    private ErrorConstants() {
        throw new AssertionError("Cannot instantiate error constants class");
    }
}
