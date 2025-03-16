package ru.axiomatika.creditsystem.service;

import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

public interface LoanContractService {
    void saveContract(LoanContract contract);

    void signContract(LoanContract contract);

    LoanContract getContractById(Long id);

    List<LoanContract> getSignedContracts();

    List<LoanContract> getAllContracts();

    // Новый метод для обработки подписания договора
    String processContractSigning(Long contractId, Model model);
}