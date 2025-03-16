package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.service.LoanApplicationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            // Одобряем сумму: от 50% до 100% от желаемой суммы
            BigDecimal approvalPercentage = BigDecimal.valueOf(0.5 + (random.nextDouble() * 0.5)); // 50-100%
            loanApplication.setApprovedAmount(
                    loanApplication.getDesiredLoanAmount()
                            .multiply(approvalPercentage)
                            .setScale(2, RoundingMode.HALF_UP) // Округляем до 2 знаков
            );
            // Случайный срок от 1 до 12 месяцев
            loanApplication.setApprovedTermMonths(random.nextInt(12) + 1); // 1-12 месяцев
        } else {
            loanApplication.setStatus("Отклонён");
            loanApplication.setApprovedAmount(BigDecimal.ZERO); // Устанавливаем 0 вместо null
            loanApplication.setApprovedTermMonths(0);
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