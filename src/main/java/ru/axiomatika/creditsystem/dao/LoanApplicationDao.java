package ru.axiomatika.creditsystem.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.axiomatika.creditsystem.entity.LoanApplication;

import java.util.List;

@Repository
public class LoanApplicationDao extends AbstractDao<LoanApplication> {
    // Конструктор, который вызывает конструктор родительского класса для задания типа сущности
    public LoanApplicationDao() {
        super(LoanApplication.class);
    }

    // Метод для получения всех одобренных заявок
    public List<LoanApplication> getApprovedApplications() {
        Session session = sessionFactory.getCurrentSession();
        // Используем HQL (Hibernate Query Language) для запроса одобренных заявок
        return session.createQuery("FROM LoanApplication WHERE status = 'Одобрен'", LoanApplication.class).list();
    }
}