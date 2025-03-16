package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.impl.ClientServiceImpl;
import ru.axiomatika.creditsystem.service.impl.LoanApplicationServiceImpl;
import ru.axiomatika.creditsystem.service.impl.LoanContractServiceImpl;

import java.math.BigDecimal;

@Controller
public class LoanApplicationController {
    private final LoanApplicationServiceImpl loanApplicationService;

    public LoanApplicationController(LoanApplicationServiceImpl loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/loan-application")
    public String showLoanApplicationForm(Model model) {
        model.addAttribute("client", new Client());
        return "loan-application-form";
    }

    @PostMapping("/loan-application")
    public String createLoanApplication(
            @ModelAttribute("client") Client client,
            @RequestParam("desiredLoanAmount") BigDecimal desiredLoanAmount,
            Model model) {
        return loanApplicationService.processLoanApplication(client, desiredLoanAmount, model);
    }
}