package ru.axiomatika.creditsystem.service.impl;

import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.service.LoanApplicationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final LoanApplicationDao loanApplicationDao = new LoanApplicationDao();
    private final Random random = new Random();

    // Логика принятия решения о выдаче кредита
    @Override
    public void applyForLoan(LoanApplication application) {
        boolean isApproved = random.nextBoolean();
        if (isApproved) {
            application.setStatus("Одобрен");
            application.setApprovedAmount(application.getClient().getDesiredLoanAmount());
            application.setApprovedTermMonths(random.nextInt(36) + 12);
        }
        else {
            application.setStatus("Отклонен");
            application.setApprovedAmount(BigDecimal.ZERO);
            application.setApprovedTermMonths(0);
        }
        loanApplicationDao.save(application);
    }

    @Override
    public LoanApplication getApplicationById(Long id) {
        return loanApplicationDao.getById(id);
    }

    @Override
    public List<LoanApplication> getApprovedApplications() {
        return loanApplicationDao.getApprovedApplications();
    }

    @Override
    public List<LoanApplication> getAllApplications() {
        return loanApplicationDao.getAll();
    }
}
