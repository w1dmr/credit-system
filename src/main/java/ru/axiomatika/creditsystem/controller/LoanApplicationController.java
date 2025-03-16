package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.impl.ClientServiceImpl;
import ru.axiomatika.creditsystem.service.impl.LoanApplicationServiceImpl;
import ru.axiomatika.creditsystem.service.impl.LoanContractServiceImpl;

@Controller
public class LoanApplicationController {
    private final ClientServiceImpl clientService;
    private final LoanApplicationServiceImpl loanApplicationService;
    private final LoanContractServiceImpl loanContractService;

    public LoanApplicationController(ClientServiceImpl clientService,
                                     LoanApplicationServiceImpl loanApplicationService,
                                     LoanContractServiceImpl loanContractService) {
        this.clientService = clientService;
        this.loanApplicationService = loanApplicationService;
        this.loanContractService = loanContractService;
    }

    @GetMapping("/loan-application")
    public String showLoanApplicationForm(Model model) {
        model.addAttribute("client", new Client());
        return "loan-application-form";
    }

    @PostMapping("/loan-application")
    @Transactional
    public String createLoanApplication(@ModelAttribute("client") Client client, Model model) {
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
        loanApplicationService.applyForLoan(loanApplication); // Логика принятия решения
        loanApplicationService.saveLoanApplication(loanApplication);

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