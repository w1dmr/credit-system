package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.impl.ClientServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showClientsPage(Model model) {
        model.addAttribute("client", new Client());
        return "clients";
    }

    @GetMapping("/all")
    public String showAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients";
    }

    @PostMapping("/search")
    public String searchClient(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value = "passportData", required = false) String passportData,
            Model model) {
        // Очищаем все параметры от пробелов сразу
        String trimmedPhone = phone != null ? phone.trim() : null;
        String trimmedLastName = lastName != null ? lastName.trim() : null;
        String trimmedFirstName = firstName != null ? firstName.trim() : null;
        String trimmedMiddleName = middleName != null ? middleName.trim() : null;
        String trimmedPassportData = passportData != null ? passportData.trim() : null;

        List<Client> clients;
        if (trimmedPhone != null && !trimmedPhone.isEmpty()) {
            clients = clientService.getClientsByPhone(trimmedPhone);
        } else if ((trimmedLastName != null && !trimmedLastName.isEmpty()) ||
                (trimmedFirstName != null && !trimmedFirstName.isEmpty()) ||
                (trimmedMiddleName != null && !trimmedMiddleName.isEmpty())) {
            clients = clientService.getClientsByFullName(trimmedFirstName, trimmedLastName, trimmedMiddleName);
        } else if (trimmedPassportData != null && !trimmedPassportData.isEmpty()) {
            Client client = clientService.getClientByPassport(trimmedPassportData);
            clients = client != null ? List.of(client) : List.of();
        } else {
            clients = clientService.getAllClients();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients";
    }
}