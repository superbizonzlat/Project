package com.test.Project.models;

import com.test.Project.services.PlaceService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sport_fields")
@Getter
@Setter
public class SportFields {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;


    @Column(name = "name")
    private String name;

    private int price;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public SportFields() {
    }
}
