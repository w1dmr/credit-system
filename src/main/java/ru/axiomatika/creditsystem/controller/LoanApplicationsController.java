package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.service.impl.LoanApplicationServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/loan-applications")
public class LoanApplicationsController {
    private final LoanApplicationServiceImpl loanApplicationService;

    public LoanApplicationsController(LoanApplicationServiceImpl loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping
    public String showApprovedApplications(Model model) {
        List<LoanApplication> applications = loanApplicationService.getApprovedApplications();
        model.addAttribute("loanApplications", applications);
        return "loan-applications";
    }
}