package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.dao.LoanContractDao;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.LoanContractService;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanContractServiceImpl implements LoanContractService {
    private final LoanContractDao loanContractDao;

    // Конструктор для инициализации всех зависимостей
    public LoanContractServiceImpl(LoanContractDao loanContractDao) {
        this.loanContractDao = loanContractDao;
    }

    @Transactional
    @Override
    public void saveContract(LoanContract contract) {
        loanContractDao.save(contract); // Сохраняет договор в базе данных
    }

    @Transactional
    @Override
    public void signContract(LoanContract contract) {
        if ("Не подписан".equals(contract.getSignatureStatus())) {  // Проверка на статус
            contract.setSignatureStatus("Подписан");    // Обновление статуса
            contract.setContractDate(LocalDate.now());  // Установка даты договора
            loanContractDao.save(contract); // Сохранение изменений
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanContract getContractById(Long id) {
        return loanContractDao.getById(id); // Получение договора по ID
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanContract> getSignedContracts() {
        return loanContractDao.getSignedContracts();    // Получение подписанных договоров
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanContract> getAllContracts() {
        return loanContractDao.getAll();    // Получение всех договоров
    }

    @Transactional
    @Override
    public String processContractSigning(Long contractId, Model model) {
        LoanContract loanContract = getContractById(contractId);    // Получение договора
        if (loanContract != null && "Не подписан".equals(loanContract.getSignatureStatus())) {
            signContract(loanContract); // Подписание контракта
            model.addAttribute("loanContract", loanContract);   // Добавление договора в модель
            return "contract-signed";   // Возвращение представления для подписанного договора
        }
        return "error"; // Возвращение представления ошибки, если договор не найден или уже подписан
    }
}