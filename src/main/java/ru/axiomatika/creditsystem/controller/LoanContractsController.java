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

    public LoanContractsController(LoanContractServiceImpl loanContractService) {
        this.loanContractService = loanContractService;
    }

    @GetMapping
    public String showSignedContracts(Model model) {
        List<LoanContract> contracts = loanContractService.getSignedContracts();
        model.addAttribute("loanContracts", contracts);
        return "loan-contracts";
    }
}