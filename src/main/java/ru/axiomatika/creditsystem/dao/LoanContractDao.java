package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

@Repository
public class LoanContractDao extends AbstractDao<LoanContract> {
    // Конструктор, который вызывает конструктор родительского класса для задания типа сущности
    public LoanContractDao() {
        super(LoanContract.class);
    }

    // Метод для получения подписанных контрактов
    public List<LoanContract> getSignedContracts() {
        Session session = sessionFactory.getCurrentSession();
        // Запрос с использованием JOIN FETCH для подгрузки связанных сущностей (LoanApplication и Client)
        return session.createQuery(
                "SELECT lc FROM LoanContract lc " +
                        "JOIN FETCH lc.loanApplication la " +   // Загрузка связанной заявки на кредит
                        "JOIN FETCH la.client " +   // Загрузка связанного клиента
                        "WHERE lc.signatureStatus = 'Подписан'",    // Фильтр по статусу "Подписан"
                LoanContract.class
        ).list();   // Возвращаем список подписанных контрактов
    }
}