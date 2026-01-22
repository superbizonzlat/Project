package com.test.Project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "sports_field")
@Getter
@Setter
public class SportsField {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;


    @Column(name = "name")
    private String name;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "sportsField",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY)
    private List<Booking> booking;

    public SportsField() {
    }
}
