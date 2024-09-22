package com.usermanagement.entities;

import com.usermanagement.constants.EntityConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = EntityConstants.EMPTY_FIRST_NAME)
    //@NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE)
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = EntityConstants.EMPTY_LAST_NAME)
    @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE)
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = EntityConstants.EMPTY_PASSWORD)
    @Size(min = 6, max = 12, message = EntityConstants.PASSWORD_SIZE)
    @Column(name = "password")
    private String password;

    @NotNull(message = EntityConstants.EMPTY_EMAIL)
    @Email(message = EntityConstants.VALID_EMAIL)
    @Column(name = "email", unique = true)
    private String email;

    public User(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = EntityConstants.EMPTY_FIRST_NAME) @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = EntityConstants.EMPTY_FIRST_NAME) @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE) String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = EntityConstants.EMPTY_LAST_NAME) @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = EntityConstants.EMPTY_LAST_NAME) @Size(min = 2, max = 30, message = EntityConstants.NAME_SIZE) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = EntityConstants.EMPTY_PASSWORD) @Size(min = 6, max = 12, message = EntityConstants.PASSWORD_SIZE) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = EntityConstants.EMPTY_PASSWORD) @Size(min = 6, max = 12, message = EntityConstants.PASSWORD_SIZE) String password) {
        this.password = password;
    }

    public @NotNull(message = EntityConstants.EMPTY_EMAIL) @Email(message = EntityConstants.VALID_EMAIL) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = EntityConstants.EMPTY_EMAIL) @Email(message = EntityConstants.VALID_EMAIL) String email) {
        this.email = email;
    }
}
