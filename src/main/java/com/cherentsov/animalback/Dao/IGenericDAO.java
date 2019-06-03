package com.cherentsov.animalback.Dao;

import com.cherentsov.animalback.Model.*;

import java.util.List;

public interface IGenericDAO {
    <T> Long create(final T newInstance);

    <T> T read(Class<T> persistClass, final Long identifier);

    <T> void update(final T transientInstance);

    <T> void delete(final T persistentInstance);

    <T> void deleteById(Class<T> persistClass, final Long identifier);

    <T> List<T> findByName(final Class<T> persistClass, final String name);

    <T> List<T> getAll(final Class<T> persistClass);

    List<Pet> findPointByFK(final List<Region> lCountry, final List<AnimalType> lAnimalType, final List<SkinColor> lSkinColor);
}
