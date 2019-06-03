package com.cherentsov.animalback.Service;

import com.cherentsov.animalback.Dao.*;
import com.cherentsov.animalback.Model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Scope("singleton")
public class DBService implements IDBService {

    private static SessionFactory sessionFactory;
    private static final Log logger = LogFactory.getLog(DBService.class);
    private static DBService instance;

    public static synchronized DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
            Configuration configuration = getDBConfiguration();
            sessionFactory = createSessionFactory(configuration);
        }
        return instance;
    }

    @Override
    public void Close() {
        sessionFactory.close();
    }

    private static Configuration getDBConfiguration() {
        logger.info("Конфиг соединения с БД: " + DBConfig.getInstance().toString());

        Locale.setDefault(Locale.US);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(AnimalType.class);
        configuration.addAnnotatedClass(Location.class);
        configuration.addAnnotatedClass(Pet.class);
        configuration.addAnnotatedClass(Region.class);
        configuration.addAnnotatedClass(SkinColor.class);

        configuration.setProperty("hibernate.dialect", DBConfig.getInstance().getDialect());
        configuration.setProperty("hibernate.connection.driver_class", DBConfig.getInstance().getDriver_class());
        configuration.setProperty("hibernate.connection.url", DBConfig.getInstance().getUrl());
        configuration.setProperty("hibernate.connection.username", DBConfig.getInstance().getUsername());
        configuration.setProperty("hibernate.connection.password", DBConfig.getInstance().getPassword());
        configuration.setProperty("hibernate.show_sql", DBConfig.getInstance().getShow_sql());
        configuration.setProperty("hibernate.hbm2ddl.auto", DBConfig.getInstance().getHbm2ddl());
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public <T> Long create(T entity) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            Long id = dao.create(entity);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public <T> T read(Class<T> persistClass, Long id) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            if (persistClass == Location.class){
                session.enableFetchProfile("location-with-region");
            }
            if (persistClass == Pet.class){
                session.enableFetchProfile("pet-with-location");
                session.enableFetchProfile("pet-with-animalType");
                session.enableFetchProfile("pet-with-skinColor");
                session.enableFetchProfile("location-with-region");
            }
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            T result = dao.read(persistClass, id);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public <T> List<T> getAll(Class<T> persistClass) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            if (persistClass == Location.class){
                session.enableFetchProfile("location-with-region");
            }
            if (persistClass == Pet.class){
                session.enableFetchProfile("pet-with-location");
                session.enableFetchProfile("pet-with-animalType");
                session.enableFetchProfile("pet-with-skinColor");
                session.enableFetchProfile("location-with-region");
            }
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<T> result = dao.getAll(persistClass);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public <T> List<T> findByName(Class<T> persistClass, String name) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            if (persistClass == Location.class){
                session.enableFetchProfile("location-with-region");
            }
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<T> result = dao.findByName(persistClass, name);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public List<Pet> findPetByFK(List<Region> lRegion, List<AnimalType> lAnimalType, List<SkinColor> lSkinColor) {
        try {
            Session session = sessionFactory.openSession();
            session.enableFetchProfile("pet-with-location");
            session.enableFetchProfile("pet-with-animalType");
            session.enableFetchProfile("pet-with-skinColor");
            session.enableFetchProfile("location-with-region");
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<Pet> result = dao.findPointByFK(lRegion, lAnimalType, lSkinColor);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public <T> void deleteById(Class<T> persistClass, Long id) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            dao.deleteById(persistClass, id);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public <T> void update(T entity) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            dao.update(entity);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

}