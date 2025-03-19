package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.Client;

import java.util.List;

@Repository
public class ClientDao extends AbstractDao<Client> {
    // Конструктор, который вызывает конструктор родительского класса для задания типа сущности
    public ClientDao() {
        super(Client.class);
    }

    // Метод для поиска клиентов по телефону
    public List<Client> getByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        // Используем HQL (Hibernate Query Language) для запроса по номеру телефона
        Query<Client> query = session.createQuery("FROM Client WHERE phone = :phone", Client.class);
        query.setParameter("phone", phone); // Устанавливаем параметр для поиска
        return query.list();    // Возвращаем результат
    }

    // Метод для поиска клиента по данным паспорта
    public Client getByPassportData(String passportData) {
        Session session = sessionFactory.getCurrentSession();
        // Используем HQL для запроса по номеру паспорта (ожидаем уникальный результат)
        return session.createQuery("FROM Client WHERE passportData = :passportData", Client.class)
                .setParameter("passportData", passportData)
                .uniqueResult();
    }

    // Метод для поиска клиентов по полному имени
    public List<Client> getByFullName(String firstName, String lastName, String middleName) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("FROM Client WHERE 1=1");

        // Добавляем условия в запрос, если параметры непустые
        if (lastName != null && !lastName.isEmpty()) {
            hql.append(" AND lower(lastName) = lower(:lastName)");
        }
        if (firstName != null && !firstName.isEmpty()) {
            hql.append(" AND lower(firstName) = lower(:firstName)");
        }
        if (middleName != null && !middleName.isEmpty()) {
            hql.append(" AND lower(middleName) = lower(:middleName)");
        }

        // Создаем запрос с динамическим HQL
        Query<Client> query = session.createQuery(hql.toString(), Client.class);

        // Устанавливаем параметры для запроса, если они не пустые
        if (lastName != null && !lastName.isEmpty()) {
            query.setParameter("lastName", lastName);
        }
        if (firstName != null && !firstName.isEmpty()) {
            query.setParameter("firstName", firstName);
        }
        if (middleName != null && !middleName.isEmpty()) {
            query.setParameter("middleName", middleName);
        }

        return query.list();    // Возвращаем список клиентов
    }

    // Метод для поиска клиентов по всем параметрам
    public List<Client> searchClients(String phone, String lastName, String firstName, String middleName, String passportData) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("FROM Client WHERE 1=1");

        // Добавляем условия в запрос, если параметры непустые
        if (phone != null && !phone.isEmpty()) {
            hql.append(" AND phone = :phone");
        }
        if (lastName != null && !lastName.isEmpty()) {
            hql.append(" AND lower(lastName) = lower(:lastName)");
        }
        if (firstName != null && !firstName.isEmpty()) {
            hql.append(" AND lower(firstName) = lower(:firstName)");
        }
        if (middleName != null && !middleName.isEmpty()) {
            hql.append(" AND lower(middleName) = lower(:middleName)");
        }
        if (passportData != null && !passportData.isEmpty()) {
            hql.append(" AND passportData = :passportData");
        }

        // Создаем запрос с динамическим HQL
        Query<Client> query = session.createQuery(hql.toString(), Client.class);

        // Устанавливаем параметры для запроса, если они не пустые
        if (phone != null && !phone.isEmpty()) {
            query.setParameter("phone", phone);
        }
        if (lastName != null && !lastName.isEmpty()) {
            query.setParameter("lastName", lastName);
        }
        if (firstName != null && !firstName.isEmpty()) {
            query.setParameter("firstName", firstName);
        }
        if (middleName != null && !middleName.isEmpty()) {
            query.setParameter("middleName", middleName);
        }
        if (passportData != null && !passportData.isEmpty()) {
            query.setParameter("passportData", passportData);
        }

        return query.list(); // Возвращаем список клиентов
    }
}