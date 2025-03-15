package ru.axiomatika.creditsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.axiomatika.creditsystem.dao.ClientDao;
import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.dao.LoanContractDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.entity.LoanContract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CreditSystemApplication {

    public static void main(String[] args) {
//        SpringApplication.run(CreditSystemApplication.class, args);
        ClientDao clientDao = new ClientDao();
        LoanApplicationDao loanApplicationDao = new LoanApplicationDao();
        LoanContractDao loanContractDao = new LoanContractDao();

        Client client = clientDao.getById(1L);
        clientDao.delete(client);
    }
}
