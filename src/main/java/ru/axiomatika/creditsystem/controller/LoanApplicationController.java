package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.impl.LoanApplicationServiceImpl;

import java.math.BigDecimal;

@Controller
public class LoanApplicationController {
    private final LoanApplicationServiceImpl loanApplicationService;

    // Внедрение зависимости через конструктор
    public LoanApplicationController(LoanApplicationServiceImpl loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    // Показ формы для подачи заявки на кредит
    @GetMapping("/loan-application")
    public String showLoanApplicationForm(Model model) {
        model.addAttribute("client", new Client()); // Добавление объекта Client для заполнения формы
        return "loan-application-form"; // Возвращает представление формы заявки на кредит
    }

    // Обработка данных формы заявки на кредит
    @PostMapping("/loan-application")
    public String createLoanApplication(
            @ModelAttribute("client") Client client,    // Получение объекта Client из формы
            @RequestParam("desiredLoanAmount") BigDecimal desiredLoanAmount,    // Получение желаемой суммы кредита
            Model model) {
        // Обработка заявки на кредит с помощью сервиса
        return loanApplicationService.processLoanApplication(client, desiredLoanAmount, model);
    }
}