package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.impl.LoanContractServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/loan-contracts")
public class LoanContractsController {
    private final LoanContractServiceImpl loanContractService;

    // Внедрение зависимости через конструктор
    public LoanContractsController(LoanContractServiceImpl loanContractService) {
        this.loanContractService = loanContractService;
    }

    // Показ подписанных кредитных договоров
    @GetMapping
    public String showSignedContracts(Model model) {
        // Получение списка подписанных кредитных договоров
        List<LoanContract> contracts = loanContractService.getSignedContracts();
        // Добавление списка договоров в модель
        model.addAttribute("loanContracts", contracts);
        // Возвращает представление с подписанными договорами
        return "loan-contracts";
    }
}