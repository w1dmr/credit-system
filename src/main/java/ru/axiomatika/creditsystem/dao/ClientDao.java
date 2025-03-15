package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.util.HibernateUtil;

import java.util.List;

public class ClientDao extends AbstractDao<Client> {
    public ClientDao() {
        super(Client.class);
    }

    // Поиск клиента по номеру телефона
    public Client getByPhone(String phone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE phone = :phone", Client.class)
                    .setParameter("phone", phone)
                    .uniqueResult();
        }
    }

    // Поиск клиента по паспортным данным
    public Client getByPassportData(String passportData) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Client WHERE passportData = :passportData", Client.class)
                    .setParameter("passportData", passportData)
                    .uniqueResult();
        }
    }

    // Поиск клиента по ФИО
    public Client getByFullName(String firstName, String lastName, String middleName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE firstName = :firstName AND lastName = :lastName AND middleName = :middleName", Client.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .setParameter("middleName", middleName)
                    .uniqueResult();
        }
    }
}