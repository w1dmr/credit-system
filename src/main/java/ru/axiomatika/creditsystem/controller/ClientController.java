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

    // Внедрение зависимостей через конструктор
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    // Показ страницы списка клиентов
    @GetMapping
    public String showClientsPage(Model model) {
        model.addAttribute("client", new Client()); // Добавление нового объекта Client в модель
        return "clients";   // Возвращает представление с формой для вывода информации о клиентах
    }

    // Показ всех клиентов
    @GetMapping("/all")
    public String showAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();   // Получение всех клиентов
        model.addAttribute("clients", clients); // Добавление списка клиентов в модель
        model.addAttribute("client", new Client()); // Добавление нового объекта Client в модель
        return "clients";   // Возвращает представление с клиентами
    }

    // Поиск клиентов
    @PostMapping("/search")
    public String searchClient(
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value = "passportData", required = false) String passportData,
            Model model) {
        // Выполняет поиск клиентов по указанным параметрам
        List<Client> clients = clientService.searchClients(phone, lastName, firstName, middleName, passportData);
        model.addAttribute("clients", clients); // Добавление результатов поиска в модель
        model.addAttribute("client", new Client()); // Добавление нового объекта Client в модель
        return "clients";   // Возвращает представление с результатами поиска клиентов
    }
}