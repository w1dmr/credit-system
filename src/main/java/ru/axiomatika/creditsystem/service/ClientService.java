package ru.axiomatika.creditsystem.service;

import ru.axiomatika.creditsystem.entity.Client;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();

    Client getClientByPhone(String phone);

    Client getClientByPassport(String passportData);

    Client getClientByFullName(String fullName);
}
