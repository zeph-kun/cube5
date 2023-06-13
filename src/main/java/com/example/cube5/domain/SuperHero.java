package com.example.cube5.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
public class SuperHero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String location;
    private Float latitude;
    private Float longitude;
    private String cellphone;
    private Integer numberIncidents;

    public Integer getNumberIncidents() {
        return numberIncidents;
    }

    public void setNumberIncidents(Integer numberIncidents) {
        this.numberIncidents = numberIncidents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperHero superHero = (SuperHero) o;

        if (!Objects.equals(id, superHero.id)) return false;
        if (!nickname.equals(superHero.nickname)) return false;
        if (!location.equals(superHero.location)) return false;
        if (!latitude.equals(superHero.latitude)) return false;
        if (!longitude.equals(superHero.longitude)) return false;
        return cellphone.equals(superHero.cellphone);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + nickname.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        result = 31 * result + cellphone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuperHero{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", location='" + location + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
