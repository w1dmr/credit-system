package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.ClientService;
import ru.axiomatika.creditsystem.service.LoanApplicationService;
import ru.axiomatika.creditsystem.service.LoanContractService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final LoanApplicationDao loanApplicationDao;
    private final ClientService clientService; // Добавляем зависимость
    private final LoanContractService loanContractService; // Добавляем зависимость
    private final Random random = new Random();

    public LoanApplicationServiceImpl(LoanApplicationDao loanApplicationDao,
                                      ClientService clientService,
                                      LoanContractService loanContractService) {
        this.loanApplicationDao = loanApplicationDao;
        this.clientService = clientService;
        this.loanContractService = loanContractService;
    }

    @Transactional
    @Override
    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationDao.save(loanApplication);
    }

    @Transactional
    @Override
    public void applyForLoan(LoanApplication loanApplication) {
        boolean isApproved = random.nextBoolean();

        if (isApproved) {
            loanApplication.setStatus("Одобрен");
            BigDecimal approvalPercentage = BigDecimal.valueOf(0.5 + (random.nextDouble() * 0.5));
            loanApplication.setApprovedAmount(
                    loanApplication.getDesiredLoanAmount()
                            .multiply(approvalPercentage)
                            .setScale(2, RoundingMode.HALF_UP)
            );
            loanApplication.setApprovedTermMonths(random.nextInt(12) + 1);
        } else {
            loanApplication.setStatus("Отклонён");
            loanApplication.setApprovedAmount(BigDecimal.ZERO);
            loanApplication.setApprovedTermMonths(0);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanApplication getApplicationById(Long id) {
        return loanApplicationDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getApprovedApplications() {
        return loanApplicationDao.getApprovedApplications();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getAllApplications() {
        return loanApplicationDao.getAll();
    }

    @Transactional
    @Override
    public String processLoanApplication(Client client, BigDecimal desiredLoanAmount, Model model) {
        // Проверяем, существует ли клиент с такими паспортными данными
        Client existingClient = clientService.getClientByPassport(client.getPassportData());

        if (existingClient != null) {
            // Если клиент существует, используем его
            client = existingClient;
        } else {
            // Если клиента нет, сохраняем нового
            clientService.saveClient(client);
        }

        // Создаём новую заявку на кредит
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setClient(client);
        loanApplication.setDesiredLoanAmount(desiredLoanAmount);
        applyForLoan(loanApplication); // Логика принятия решения
        saveLoanApplication(loanApplication);

        // Создаём черновик договора
        LoanContract loanContract = new LoanContract();
        loanContract.setLoanApplication(loanApplication);
        loanContract.setContractDate(java.time.LocalDate.now());

        if ("Одобрен".equals(loanApplication.getStatus())) {
            loanContract.setSignatureStatus("Не подписан");
            loanContractService.saveContract(loanContract);
            model.addAttribute("loanContract", loanContract);
            return "loan-contract-sign"; // Перенаправляем на страницу подписания
        } else {
            loanContract.setSignatureStatus("Отклонён");
            loanContractService.saveContract(loanContract);
            model.addAttribute("loanContract", loanContract);
            return "loan-rejected";
        }
    }
}