package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;

import java.util.List;

public interface LoanApplicationService {
    void saveLoanApplication(LoanApplication loanApplication);

    void applyForLoan(LoanApplication loanApplication);

    LoanApplication getApplicationById(Long id);

    List<LoanApplication> getApprovedApplications();

    List<LoanApplication> getAllApplications();
}
