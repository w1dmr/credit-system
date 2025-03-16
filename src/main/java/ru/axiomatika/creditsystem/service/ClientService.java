package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.Client;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();

    List<Client> getClientsByPhone(String phone);

    Client getClientByPassport(String passportData);

    List<Client> getClientsByFullName(String firstName, String lastName, String middleName);
}