package com.test.Project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "place")
@Getter
@Setter
public class Place {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "place",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<SportsField> sportFields;
    public Place() {
    }

}
