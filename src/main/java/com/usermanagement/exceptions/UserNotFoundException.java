package com.usermanagement.exceptions;

import lombok.Data;

@Data
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(int userID) {
        super("User id not found: "+ userID);
    }
}
