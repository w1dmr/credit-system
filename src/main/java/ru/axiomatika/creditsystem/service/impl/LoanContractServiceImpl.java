package ru.axiomatika.creditsystem.service.impl;

import ru.axiomatika.creditsystem.dao.LoanContractDao;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.LoanContractService;

import java.time.LocalDate;
import java.util.List;

public class LoanContractServiceImpl implements LoanContractService {
    private final LoanContractDao loanContractDao = new LoanContractDao();

    @Override
    public void signContract(LoanContract contract) {
        contract.setSignatureStatus("Подписан");
        contract.setContractDate(LocalDate.now());
        loanContractDao.save(contract);
    }

    @Override
    public LoanContract getContractById(Long id) {
        return loanContractDao.getById(id);
    }

    @Override
    public List<LoanContract> getSignedContracts() {
        return loanContractDao.getSignedContracts();
    }

    @Override
    public List<LoanContract> getAllContracts() {
        return loanContractDao.getAll();
    }
}