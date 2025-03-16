package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.Client;

import java.util.List;

@Repository
public class ClientDao extends AbstractDao<Client> {
    public ClientDao() {
        super(Client.class);
    }

    public List<Client> getByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("FROM Client WHERE phone = :phone", Client.class);
        query.setParameter("phone", phone);
        return query.list();
    }

    public Client getByPassportData(String passportData) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client WHERE passportData = :passportData", Client.class)
                .setParameter("passportData", passportData)
                .uniqueResult();
    }

    public List<Client> getByFullName(String firstName, String lastName, String middleName) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("FROM Client WHERE 1=1");

        if (lastName != null && !lastName.isEmpty()) {
            hql.append(" AND lower(lastName) = lower(:lastName)");
        }
        if (firstName != null && !firstName.isEmpty()) {
            hql.append(" AND lower(firstName) = lower(:firstName)");
        }
        if (middleName != null && !middleName.isEmpty()) {
            hql.append(" AND lower(middleName) = lower(:middleName)");
        }

        Query<Client> query = session.createQuery(hql.toString(), Client.class);

        if (lastName != null && !lastName.isEmpty()) {
            query.setParameter("lastName", lastName);
        }
        if (firstName != null && !firstName.isEmpty()) {
            query.setParameter("firstName", firstName);
        }
        if (middleName != null && !middleName.isEmpty()) {
            query.setParameter("middleName", middleName);
        }

        return query.list();
    }
}