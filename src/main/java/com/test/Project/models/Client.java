package com.test.Project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "client",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<Booking> booking;

    public Client() {
    }

    public Client(String lastname, String firstname, String telephone, String mail) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.telephone = telephone;
        this.mail = mail;
    }
}
