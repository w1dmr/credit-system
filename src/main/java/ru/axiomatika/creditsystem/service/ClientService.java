package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.Client;

import java.util.List;

public interface ClientService {
    // Сохранение клиента
    void saveClient(Client client);

    // Получение клиента по ID
    Client getClientById(Long id);

    // Получение списка всех клиентов
    List<Client> getAllClients();

    // Получение списка клиентов по номеру телефона
    List<Client> getClientsByPhone(String phone);

    // Получение клиента по паспортным данным
    Client getClientByPassport(String passportData);

    // Получение клиентов по полному имени
    List<Client> getClientsByFullName(String firstName, String lastName, String middleName);

    // Поиск клиентов по нескольким параметрам
    List<Client> searchClients(String phone, String lastName, String firstName, String middleName, String passportData);
}