package ru.axiomatika.creditsystem.service;

import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.entity.LoanContract;

import java.util.List;

public interface LoanContractService {
    // Сохранение договора
    void saveContract(LoanContract contract);

    // Подписание договора
    void signContract(LoanContract contract);

    // Получение договора по ID
    LoanContract getContractById(Long id);

    // Получение списка подписанных договоров
    List<LoanContract> getSignedContracts();

    // Получение списка всех договоров
    List<LoanContract> getAllContracts();

    // Обработка подписания договора и возврат результата
    String processContractSigning(Long contractId, Model model);
}