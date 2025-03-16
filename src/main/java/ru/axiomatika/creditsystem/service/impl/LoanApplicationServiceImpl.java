package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.service.LoanApplicationService;

import java.util.List;
import java.util.Random;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final LoanApplicationDao loanApplicationDao;
    private final Random random = new Random(); // Генератор случайных чисел

    public LoanApplicationServiceImpl(LoanApplicationDao loanApplicationDao) {
        this.loanApplicationDao = loanApplicationDao;
    }

    @Transactional
    @Override
    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationDao.save(loanApplication);
    }

    @Transactional
    @Override
    public void applyForLoan(LoanApplication loanApplication) {
        // Случайное решение: 50% шанс на одобрение
        boolean isApproved = random.nextBoolean();

        if (isApproved) {
            loanApplication.setStatus("Одобрен");
            loanApplication.setApprovedAmount(loanApplication.getClient().getDesiredLoanAmount());
            // Случайный срок от 6 до 36 месяцев
            loanApplication.setApprovedTermMonths(random.nextInt(31) + 6); // 31 = 36 - 6 + 1
        } else {
            loanApplication.setStatus("Отклонён");
            loanApplication.setApprovedAmount(null);
            loanApplication.setApprovedTermMonths(null);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanApplication getApplicationById(Long id) {
        return loanApplicationDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getApprovedApplications() {
        return loanApplicationDao.getApprovedApplications();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getAllApplications() {
        return loanApplicationDao.getAll();
    }
}