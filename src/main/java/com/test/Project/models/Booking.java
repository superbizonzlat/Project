package com.test.Project.models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class  Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fields")
    private SportsField sportsField;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime begin_at;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime end_at;

    public Booking(){

    }

}
