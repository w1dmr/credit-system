package ru.axiomatika.creditsystem.service.impl;

import ru.axiomatika.creditsystem.dao.ClientDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao = new ClientDao();

    @Override
    public void saveClient(Client client) {
        clientDao.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientDao.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    @Override
    public Client getClientByPhone(String phone) {
        return clientDao.getByPhone(phone);
    }

    @Override
    public Client getClientByPassport(String passportData) {
        return clientDao.getByPassportData(passportData);
    }

    @Override
    public Client getClientByFullName(String fullName) {
        String[] parts = fullName.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Full name should consist of three parts: surname, first name, middle name");
        }

        String lastName = parts[0];     // Фамилия
        String firstName = parts[1];    // Имя
        String middleName = parts[2];   // Отчество

        return clientDao.getByFullName(lastName, firstName, middleName);
    }
}
