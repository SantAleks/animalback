package com.cherentsov.animalback.Model;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@FetchProfiles({
        @FetchProfile(fetchOverrides = { @FetchProfile.FetchOverride(association = "location", entity = Pet.class, mode = FetchMode.JOIN) }, name = "pet-with-location"),
        @FetchProfile(fetchOverrides = { @FetchProfile.FetchOverride(association = "animalType", entity = Pet.class, mode = FetchMode.JOIN) }, name = "pet-with-animalType"),
        @FetchProfile(fetchOverrides = { @FetchProfile.FetchOverride(association = "skinColor", entity = Pet.class, mode = FetchMode.JOIN) }, name = "pet-with-skinColor")
})
@Entity
@Table(name="pet")
public class Pet implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="pet_seq",sequenceName="o_pet_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pet_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="location_fk", nullable = false)
    private Location location;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="animalType_fk", nullable = false)
    private AnimalType animalType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="skinColor_fk", nullable = false)
    private SkinColor skinColor;

    public Pet() {
    }

    public Pet(Long id, String name, Location location, AnimalType animalType, SkinColor skinColor) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.animalType = animalType;
        this.skinColor = skinColor;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public SkinColor getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(SkinColor skinColor) {
        this.skinColor = skinColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) &&
                Objects.equals(name, pet.name) &&
                Objects.equals(location, pet.location) &&
                Objects.equals(animalType, pet.animalType) &&
                Objects.equals(skinColor, pet.skinColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, animalType, skinColor);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", animalType=" + animalType +
                ", skinColor=" + skinColor +
                '}';
    }
}
