package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

public interface LoanContractService {
    void signContract(LoanContract contract);

    LoanContract getContractById(Long id);

    List<LoanContract> getSignedContracts();

    List<LoanContract> getAllContracts();
}
