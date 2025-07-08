package com.sa.imoveis.model;

import jakarta.persistence.*;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int numberOfRooms;
    @Column(nullable = false)
    private int numberOfBathrooms;
    @Column(nullable = false)
    private int numberOfPossibleCars;
    @Column(nullable = false)
    private double landSize;
    @Column(nullable = false)
    private int addressNumber;
    @ManyToOne
    private Consultant consultant;
    private Double salePrice;
    private Double rentalPrice;
}
