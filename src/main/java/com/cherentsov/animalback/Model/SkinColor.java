package com.cherentsov.animalback.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="skinColor", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class SkinColor implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="skinColor_seq",sequenceName="o_skinColor_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skinColor_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    public SkinColor() {
    }

    public SkinColor(Long id, String name) {
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
        SkinColor skinColor = (SkinColor) o;
        return Objects.equals(id, skinColor.id) &&
                Objects.equals(name, skinColor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SkinColor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
