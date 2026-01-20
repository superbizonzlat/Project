package com.test.Project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;

public class ClientDTO {
    private Optional<String> lastname;

    private Optional<String> firstname;

    private Optional<@Pattern(regexp = "^(8|\\+7)\\d{10}", message = "wrong format telephone") String> telephone;

    private Optional<@Email(message = "wrong format email") String> mail;

    public String getLastname() {
        return this.lastname.orElse(null);
    }

    public void setLastname(String lastname) {
        this.lastname = Optional.of(lastname);
    }

    public String getFirstname() {
        return this.firstname.orElse(null);
    }

    public void setFirstname(String firstname) {
        this.firstname = Optional.of(firstname);
    }

    public String getTelephone() {
        return this.telephone.orElse(null);
    }

    public void setTelephone(String telephone) {
        this.telephone = Optional.of(telephone);
    }

    public String getMail() {
        return this.mail.orElse(null);
    }

    public void setMail(String mail) {
        this.mail = Optional.of(mail);
    }




}
