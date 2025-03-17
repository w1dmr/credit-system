package ru.axiomatika.creditsystem.service;

import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;

import java.math.BigDecimal;
import java.util.List;

public interface LoanApplicationService {
    // Сохранение заявки на кредит
    void saveLoanApplication(LoanApplication loanApplication);

    // Обработка заявки на кредит (одобрение/отклонение)
    void applyForLoan(LoanApplication loanApplication);

    // Получение заявки на кредит по ID
    LoanApplication getApplicationById(Long id);

    // Получение списка одобренных заявок
    List<LoanApplication> getApprovedApplications();

    // Получение списка всех заявок
    List<LoanApplication> getAllApplications();

    // Обработка заявки, создание договора и возврат результата
    String processLoanApplication(Client client, BigDecimal desiredLoanAmount, Model model);
}