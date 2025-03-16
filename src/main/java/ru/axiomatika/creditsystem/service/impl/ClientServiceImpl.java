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
    public List<Client> getClientsByPhone(String phone) {
        return clientDao.getByPhone(phone);
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByPassport(String passportData) {
        return clientDao.getByPassportData(passportData);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getClientsByFullName(String firstName, String lastName, String middleName) {
        return clientDao.getByFullName(firstName, lastName, middleName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> searchClients(String phone, String lastName, String firstName, String middleName, String passportData) {
        // Очищаем все параметры от пробелов
        String trimmedPhone = phone != null ? phone.trim() : null;
        String trimmedLastName = lastName != null ? lastName.trim() : null;
        String trimmedFirstName = firstName != null ? firstName.trim() : null;
        String trimmedMiddleName = middleName != null ? middleName.trim() : null;
        String trimmedPassportData = passportData != null ? passportData.trim() : null;

        if (trimmedPhone != null && !trimmedPhone.isEmpty()) {
            return getClientsByPhone(trimmedPhone);
        } else if ((trimmedLastName != null && !trimmedLastName.isEmpty()) ||
                (trimmedFirstName != null && !trimmedFirstName.isEmpty()) ||
                (trimmedMiddleName != null && !trimmedMiddleName.isEmpty())) {
            return getClientsByFullName(trimmedFirstName, trimmedLastName, trimmedMiddleName);
        } else if (trimmedPassportData != null && !trimmedPassportData.isEmpty()) {
            Client client = getClientByPassport(trimmedPassportData);
            return client != null ? List.of(client) : List.of();
        } else {
            return getAllClients();
        }
    }
}