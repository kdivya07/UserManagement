package com.usermanagement.constants;

public final class ErrorConstants {

    public static final String USER_NOT_FOUND = "User with ID %d not found.";
    public static final String EMAIL_ALREADY_EXISTS = "Email %s already exists.";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred.";
    public static final String DUPLICATE_EMAIL = "Duplicate email.";
    public static final String BAD_REQUEST = "Bad request.";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error.";
    public static final String RESOURCE_NOT_FOUND = "Resource not found.";

    private ErrorConstants() {
        throw new AssertionError("Cannot instantiate error constants class");
    }
}
