package com.informactos.its.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "locality")

public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false, length = 128)
    private String address;

    @Column(nullable = false, length = 64)
    private String neighborhood;

    @Column(name = "reference_point", length = 256)
    private String referencePoint;

    public Locality() {
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }
}