package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axiomatika.creditsystem.dao.ClientDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;  // Ссылка на объект ClientDao для взаимодействия с базой данных

    // Конструктор для инициализации ClientDao
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Transactional
    @Override
    public void saveClient(Client client) {
        clientDao.save(client); // Сохранение клиента в базе данных через DAO
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientById(Long id) {
        return clientDao.getById(id);   // Получение клиента по ID
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();  // Получение списка всех клиентов
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getClientsByPhone(String phone) {
        return clientDao.getByPhone(phone); // Поиск клиентов по номеру телефона
    }

    @Transactional(readOnly = true)
    @Override
    public Client getClientByPassport(String passportData) {
        return clientDao.getByPassportData(passportData);   // Поиск клиента по данным паспорта
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> getClientsByFullName(String firstName, String lastName, String middleName) {
        return clientDao.getByFullName(firstName, lastName, middleName);    // Поиск клиентов по полному имени
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

        // Логика для поиска клиентов на основе различных параметров
        if (trimmedPhone != null && !trimmedPhone.isEmpty()) {
            return getClientsByPhone(trimmedPhone); // Если есть номер телефона, ищем по телефону
        } else if ((trimmedLastName != null && !trimmedLastName.isEmpty()) ||
                (trimmedFirstName != null && !trimmedFirstName.isEmpty()) ||
                (trimmedMiddleName != null && !trimmedMiddleName.isEmpty())) {
            return getClientsByFullName(trimmedFirstName, trimmedLastName, trimmedMiddleName);  // Если есть хотя бы одно поле имени, ищем по полному имени
        } else if (trimmedPassportData != null && !trimmedPassportData.isEmpty()) {
            Client client = getClientByPassport(trimmedPassportData);   // Если есть данные паспорта, ищем по паспорту
            return client != null ? List.of(client) : List.of();    // Если клиент найден, возвращаем его в списке, иначе пустой список
        } else {
            return getAllClients(); // Если нет параметров для поиска, возвращаем всех клиентов
        }
    }
}