package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axiomatika.creditsystem.dao.LoanContractDao;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.LoanContractService;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanContractServiceImpl implements LoanContractService {
    private final LoanContractDao loanContractDao;

    public LoanContractServiceImpl(LoanContractDao loanContractDao) {
        this.loanContractDao = loanContractDao;
    }

    @Transactional
    @Override
    public void saveContract(LoanContract contract) {
        loanContractDao.save(contract); // Просто сохраняем контракт
    }

    @Transactional
    @Override
    public void signContract(LoanContract contract) {
        if ("Не подписан".equals(contract.getSignatureStatus())) {
            contract.setSignatureStatus("Подписан");
            contract.setContractDate(LocalDate.now()); // Обновляем дату при подписании
            loanContractDao.save(contract); // Сохраняем изменения после подписания
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanContract getContractById(Long id) {
        return loanContractDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanContract> getSignedContracts() {
        return loanContractDao.getSignedContracts();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanContract> getAllContracts() {
        return loanContractDao.getAll();
    }
}