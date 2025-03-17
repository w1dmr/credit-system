package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDao<T> {
    private final Class<T> entityClass;

    // Внедрение фабрики сессий для работы с Hibernate
    @Autowired
    protected SessionFactory sessionFactory;

    // Конструктор, принимающий тип сущности
    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Метод для сохранения сущности
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);    // Сохранение сущности в базу
    }

    // Метод для получения сущности по ID
    public T getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(entityClass, id);    // Получение сущности по ID
    }

    // Метод для получения всех сущностей
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();  // Получение всех записей сущности
    }

    // Метод для обновления сущности
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);  // Обновление сущности в базе
    }

    // Метод для удаления сущности
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity); // Удаление сущности из базы
    }
}