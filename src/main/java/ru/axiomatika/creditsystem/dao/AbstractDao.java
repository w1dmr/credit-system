package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDao<T> {
    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
    }

    public T getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(entityClass, id);
    }

    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
    }

    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
    }
}