package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

@Repository
public class LoanContractDao extends AbstractDao<LoanContract> {
    public LoanContractDao() {
        super(LoanContract.class);
    }

    public List<LoanContract> getSignedContracts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                "SELECT lc FROM LoanContract lc " +
                        "JOIN FETCH lc.loanApplication la " +
                        "JOIN FETCH la.client " +
                        "WHERE lc.signatureStatus = 'Подписан'",
                LoanContract.class
        ).list();
    }
}