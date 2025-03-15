package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.util.HibernateUtil;

import java.util.List;

public class LoanContractDao extends AbstractDao<LoanContract> {
    public LoanContractDao() {
        super(LoanContract.class);
    }

    // Получение всех подписанных кредитных договоров
    public List<LoanContract> getSignedContracts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LoanContract WHERE signatureStatus = 'Подписан'", LoanContract.class).list();
        }
    }
}
