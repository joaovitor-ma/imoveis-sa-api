package com.sa.imoveis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Property property;

}
