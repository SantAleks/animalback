package com.cherentsov.animalback.Service;

import com.cherentsov.animalback.Model.*;
import org.hibernate.HibernateException;

import java.util.List;

public interface IDBService {
    void Close();
    <T> Long create(final T entity) throws HibernateException;
    <T> T read(final Class<T> persistClass, final Long id)throws HibernateException;
    <T> List<T> getAll(final Class<T> persistClass) throws HibernateException;
    <T> List<T> findByName(final Class<T> persistClass, final String name) throws HibernateException;
    List<Pet> findPetByFK(final List<Region> lRegion, final List<AnimalType> lAnimalType, final List<SkinColor> lSkinColor);
    <T> void deleteById(final Class<T> persistClass, final Long id)throws HibernateException;
    <T> void update(final T entity) throws HibernateException;
}