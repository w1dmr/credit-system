package ru.axiomatika.creditsystem.service;

import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;

import java.math.BigDecimal;
import java.util.List;

public interface LoanApplicationService {
    void saveLoanApplication(LoanApplication loanApplication);

    void applyForLoan(LoanApplication loanApplication);

    LoanApplication getApplicationById(Long id);

    List<LoanApplication> getApprovedApplications();

    List<LoanApplication> getAllApplications();

    // Новый метод для обработки заявки и создания договора
    String processLoanApplication(Client client, BigDecimal desiredLoanAmount, Model model);
}