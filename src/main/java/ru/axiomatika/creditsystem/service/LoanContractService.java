package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

public interface LoanContractService {
    void saveContract(LoanContract contract); // Новый метод для сохранения

    void signContract(LoanContract contract); // Существующий метод для подписания

    LoanContract getContractById(Long id);

    List<LoanContract> getSignedContracts();

    List<LoanContract> getAllContracts();
}