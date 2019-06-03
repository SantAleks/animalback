package com.cherentsov.animalback.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="animalType", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class AnimalType implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="animalType_seq",sequenceName="o_animalType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="animalType_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    public AnimalType() {
    }

    public AnimalType(Long id, String name) {
        this.id = id;
        this.name = name;
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
        AnimalType animalType = (AnimalType) o;
        return Objects.equals(id, animalType.id) &&
                Objects.equals(name, animalType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

