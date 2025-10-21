package com.test.Project.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;

    private String login;

    private String password;

    private String lastname;

    private String firstname;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(String login, String password, String lastname, String firstname, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.userRole = userRole;
    }
}
