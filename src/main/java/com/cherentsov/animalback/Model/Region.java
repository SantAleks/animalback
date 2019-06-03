package com.cherentsov.animalback.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="region", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Region implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="region_seq",sequenceName="o_region_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="region_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    public Region() {
    }

    public Region(Long id, String name) {
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
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(name, region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

