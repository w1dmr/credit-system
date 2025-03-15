package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.util.HibernateUtil;

import java.util.List;

public class LoanApplicationDao extends AbstractDao<LoanApplication> {
    public LoanApplicationDao() {
        super(LoanApplication.class);
    }

    // Получение всех одобренных заявок
    public List<LoanApplication> getApprovedApplications() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LoanApplication WHERE status = 'Одобрен'", LoanApplication.class).list();
        }
    }
}
