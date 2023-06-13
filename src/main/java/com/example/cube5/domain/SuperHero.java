package com.example.cube5.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "superhero_incidents",
            joinColumns = @JoinColumn(name = "superhero_id"),
            inverseJoinColumns = @JoinColumn(name = "incident_id")
    )
    private Set<Incident> Incidents = new HashSet<>();

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

    public boolean canHandleIncident(Incident incident) {
        double earthRadius = 6371.0; // rayon de la Terre en km

        double lat1 = Math.toRadians(this.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lat2 = Math.toRadians(incident.getLatitude());
        double lon2 = Math.toRadians(incident.getLongitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        if (distance <= 50 && this.numberIncidents > 0) {
            System.out.println("SuperHero can handle incident in city: " + incident.getCity());
            return true;
        } else {
            System.out.println("SuperHero cannot handle incident in city: " + incident.getCity());
            return false;
        }
    }

    public boolean isInRadius(double incidentLatitude, double incidentLongitude) {
        final int EARTH_RADIUS_KM = 6371;
        double latDistance = Math.toRadians(this.latitude - incidentLatitude);
        double lonDistance = Math.toRadians(this.longitude - incidentLongitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(incidentLatitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;
        System.out.println("Is in radius: " + distance);
        return distance <= 50;
    }
}
