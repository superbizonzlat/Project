package com.test.Project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Optional<String> login;
    private Optional<String> password;
    private Optional<String> lastname;

    private Optional<String> firstname;

    public String getLogin() {
        return this.login != null ? this.login.get() : null ;
    }

    public void setLogin(String login) {
        this.login = Optional.of(login);
    }

    public String getPassword() {
        return this.password != null ? this.password.get() : null ;
    }

    public void setPassword(String password) {
        this.password = Optional.of(password);
    }

    public String getLastname() {
        return this.lastname != null ? this.lastname.get() : null ;
    }

    public void setLastname(String lastname) {
        this.lastname = Optional.of(lastname);
    }

    public String getFirstname() {
        return this.firstname != null ? this.firstname.get() : null ;
    }

    public void setFirstname(String firstname) {
        this.firstname = Optional.of(firstname);
    }
}
