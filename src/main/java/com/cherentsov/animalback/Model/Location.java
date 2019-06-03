package com.cherentsov.animalback.Model;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@FetchProfiles({
        @FetchProfile(fetchOverrides = { @FetchProfile.FetchOverride(association = "region", entity = Location.class, mode = FetchMode.JOIN) }, name = "location-with-region")
})
@Entity
@Table(name="location", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Location implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="location_seq",sequenceName="o_location_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="location_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_fk", nullable = false)
    private Region region;

    public Location(){}

    public Location(Long id, String name, Region region){
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(name, location.name) &&
                Objects.equals(region, location.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region=" + region.getName() +
                '}';
    }
}

