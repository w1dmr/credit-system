package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.axiomatika.creditsystem.service.LoanContractService;

@Controller
public class LoanContractController {
    private final LoanContractService loanContractService;

    // Внедрение зависимости через конструктор
    public LoanContractController(LoanContractService loanContractService) {
        this.loanContractService = loanContractService;
    }

    // Обработка POST-запроса для подписания кредитного договора
    @PostMapping("/sign-contract/{id}")
    public String signContract(@PathVariable("id") Long contractId, Model model) {
        // Обработка подписания кредитного договора с использованием сервиса
        return loanContractService.processContractSigning(contractId, model);
    }
}