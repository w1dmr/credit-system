package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.LoanApplication;

import java.util.List;

@Repository
public class LoanApplicationDao extends AbstractDao<LoanApplication> {
    public LoanApplicationDao() {
        super(LoanApplication.class);
    }

    public List<LoanApplication> getApprovedApplications() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM LoanApplication WHERE status = 'Одобрен'", LoanApplication.class).list();
    }
}