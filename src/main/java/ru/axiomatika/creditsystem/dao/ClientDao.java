package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.Client;

@Repository
public class ClientDao extends AbstractDao<Client> {
    public ClientDao() {
        super(Client.class);
    }

    public Client getByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client WHERE phone = :phone", Client.class)
                .setParameter("phone", phone)
                .uniqueResult();
    }

    public Client getByPassportData(String passportData) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client WHERE passportData = :passportData", Client.class)
                .setParameter("passportData", passportData)
                .uniqueResult();
    }

    public Client getByFullName(String firstName, String lastName, String middleName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client WHERE firstName = :firstName AND lastName = :lastName AND middleName = :middleName", Client.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("middleName", middleName)
                .uniqueResult();
    }
}