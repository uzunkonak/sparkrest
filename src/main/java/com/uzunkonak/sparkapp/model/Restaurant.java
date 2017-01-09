package com.uzunkonak.sparkapp.model;

import javax.persistence.*;

/**
 * Created by Caner Uzunkonak on 09.01.2017.
 */
@Entity
@Table(schema = "sparkapp", name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "CITY")
    private String city;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String city) {
        this(name, city);
        this.id = id;
    }

    public Restaurant(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
