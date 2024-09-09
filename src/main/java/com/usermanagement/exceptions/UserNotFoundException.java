package com.usermanagement.exceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(int userID) {
        super("User id not found: "+ userID);
    }
}
