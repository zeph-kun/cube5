package com.example.cube5.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;
    private Double latitude;
    private Double longitude;

    @ManyToMany(mappedBy = "numberIncidents")
    private Set<SuperHero> superHeroes = new HashSet<>();

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incident incident = (Incident) o;

        if (!Objects.equals(id, incident.id)) return false;
        return name.equals(incident.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
