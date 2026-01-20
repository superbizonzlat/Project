package com.test.Project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;

    private String lastname;

    private String firstname;

    private String telephone;

    private String mail;

    public Client() {
    }

    public Client(String lastname, String firstname, String telephone, String mail) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.telephone = telephone;
        this.mail = mail;
    }
}
