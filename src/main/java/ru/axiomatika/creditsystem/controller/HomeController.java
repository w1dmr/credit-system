package ru.axiomatika.creditsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // Обрабатывает GET-запрос на корневой URL "/"
    @GetMapping("/")
    public String home() {
        return "index.html";    // Возвращает шаблон "index.html"
    }
}
