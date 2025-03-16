package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.LoanContractService;

@Controller
public class LoanContractController {
    private final LoanContractService loanContractService;

    public LoanContractController(LoanContractService loanContractService) {
        this.loanContractService = loanContractService;
    }

    @PostMapping("/sign-contract/{id}")
    @Transactional
    public String signContract(@PathVariable("id") Long contractId, Model model) {
        LoanContract loanContract = loanContractService.getContractById(contractId);
        if (loanContract != null && "Не подписан".equals(loanContract.getSignatureStatus())) {
            loanContractService.signContract(loanContract); // Подписываем договор
            model.addAttribute("loanContract", loanContract);
            return "contract-signed"; // Перенаправляем на страницу с результатами
        }
        return "error";
    }
}