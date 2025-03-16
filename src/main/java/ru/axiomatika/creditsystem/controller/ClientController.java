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
        List<Client> clients;
        if (phone != null && !phone.trim().isEmpty()) {
            clients = clientService.getClientsByPhone(phone.trim());
        } else if ((lastName != null && !lastName.trim().isEmpty()) ||
                (firstName != null && !firstName.trim().isEmpty()) ||
                (middleName != null && !middleName.trim().isEmpty())) {
            clients = clientService.getClientsByFullName(firstName, lastName, middleName);
        } else if (passportData != null && !passportData.trim().isEmpty()) {
            Client client = clientService.getClientByPassport(passportData.trim());
            clients = client != null ? List.of(client) : List.of();
        } else {
            clients = clientService.getAllClients();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients";
    }
}