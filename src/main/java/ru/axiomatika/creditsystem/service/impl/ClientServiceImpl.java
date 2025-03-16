package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axiomatika.creditsystem.dao.ClientDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Transactional
    @Override
    public void saveClient(Client client) {
        clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientById(Long id) {
        return clientDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByPhone(String phone) {
        return clientDao.getByPhone(phone);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByPassport(String passportData) {
        return clientDao.getByPassportData(passportData);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByFullName(String fullName) {
        String[] parts = fullName.split("\\s+");
        String firstName = parts.length > 0 ? parts[0] : "";
        String lastName = parts.length > 1 ? parts[1] : "";
        String middleName = parts.length > 2 ? parts[2] : null;
        return clientDao.getByFullName(firstName, lastName, middleName);
    }
}