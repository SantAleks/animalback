package com.cherentsov.animalback.Dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.cherentsov.animalback.Model.*;
import java.util.List;

public class GenericDAO implements IGenericDAO {

    private Session session;

    public GenericDAO(Session session) {
        this.session = session;
    }

    @Override
    public <T> Long create(T entity) throws HibernateException {
        return (Long)session.save(entity);
    }

    @Override
    public <T> void update(T transientInstance) throws HibernateException{
        session.update(transientInstance);
    }

    @Override
    public <T> void delete(T persistentInstance) throws HibernateException{
        session.delete(persistentInstance);
    }

    @Override
    public <T> void deleteById(Class<T> persistClass, Long identifier) throws HibernateException{
        this.delete(this.read(persistClass ,identifier));
    }

    @Override
    public <T> T read(Class<T> persistClass, Long identifier) throws HibernateException {
        return session.get(persistClass, identifier);
    }
    @Override
    public <T> List<T> findByName(final Class<T> persistClass, String name) {
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName() +
                " where lower(name) like :parName ORDER BY name", persistClass);
        query.setParameter("parName", "%" + name.toLowerCase() + "%");
        return query.list();
    }

    public <T> List<T> getAll(final Class<T> persistClass) {
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName() + " ORDER BY name", persistClass);
        return query.list();
    }

    @Override
    public List<Pet> findPointByFK(List<Region> lRegion, List<AnimalType> lAnimalType, List<SkinColor> lSkinColor) {
        String qS = "select pt from Pet as pt";

        //Если задан регион, ограничим жывотных этим регионом
        if (lRegion.size() == 1){
            qS = qS + " join pt.location as loc with loc.region.id = " + lRegion.get(0).getId();
        }
        if (lAnimalType.size() == 1){
            qS = qS + " where pt.animalType.id = " + lAnimalType.get(0).getId();
        }
        if (lSkinColor.size() == 1){
            qS = qS + (lAnimalType.size() != 1?" where ":" and ");
            qS = qS + "skinColor.id = " + lSkinColor.get(0).getId();
        }
        qS = qS + " ORDER BY pt.animalType, pt.location";
        Query<Pet> query = session.createQuery(qS , Pet.class).setMaxResults(200);
        return query.list();
    }
}
