package ru.axiomatika.creditsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.axiomatika.creditsystem.dao.LoanApplicationDao;
import ru.axiomatika.creditsystem.entity.Client;
import ru.axiomatika.creditsystem.entity.LoanApplication;
import ru.axiomatika.creditsystem.entity.LoanContract;
import ru.axiomatika.creditsystem.service.ClientService;
import ru.axiomatika.creditsystem.service.LoanApplicationService;
import ru.axiomatika.creditsystem.service.LoanContractService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final LoanApplicationDao loanApplicationDao;    // Зависимость для взаимодействия с DAO для заявок на кредиты
    private final ClientService clientService;  // Зависимость для взаимодействия с сервисом клиентов
    private final LoanContractService loanContractService;  // Зависимость для взаимодействия с сервисом кредитных договоров
    private final Random random = new Random(); // Для случайных операций (одобрения заявки)

    // Конструктор для инициализации всех зависимостей
    public LoanApplicationServiceImpl(LoanApplicationDao loanApplicationDao,
                                      ClientService clientService,
                                      LoanContractService loanContractService) {
        this.loanApplicationDao = loanApplicationDao;
        this.clientService = clientService;
        this.loanContractService = loanContractService;
    }

    @Transactional
    @Override
    public void saveLoanApplication(LoanApplication loanApplication) {
        loanApplicationDao.save(loanApplication);   // Сохраняем заявку на кредит в базу данных
    }

    @Transactional
    @Override
    public void applyForLoan(LoanApplication loanApplication) {
        // Логика принятия решения по кредитной заявке (случайное одобрение/отклонение)
        boolean isApproved = random.nextBoolean();

        if (isApproved) {
            loanApplication.setStatus("Одобрен");   // Устанавливаем статус заявки как "Одобрен"
            BigDecimal approvalPercentage = BigDecimal.valueOf(0.5 + (random.nextDouble() * 0.5));  // Случайный процент одобрения
            loanApplication.setApprovedAmount(
                    loanApplication.getDesiredLoanAmount()
                            .multiply(approvalPercentage)   // Рассчитываем одобренную сумму
                            .setScale(2, RoundingMode.HALF_UP)  // Округляем до двух знаков после запятой
            );
            loanApplication.setApprovedTermMonths(random.nextInt(12) + 1);  // Случайный срок кредита (1-12 месяцев)
        } else {
            loanApplication.setStatus("Отклонён");  // Устанавливаем статус заявки как "Отклонён"
            loanApplication.setApprovedAmount(BigDecimal.ZERO); // Отклонённая заявка не получает одобренную сумму
            loanApplication.setApprovedTermMonths(0);   // Отклонённая заявка не имеет срока кредита
        }
    }

    @Transactional(readOnly = true)
    @Override
    public LoanApplication getApplicationById(Long id) {
        return loanApplicationDao.getById(id);  // Получаем заявку по ID
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getApprovedApplications() {
        return loanApplicationDao.getApprovedApplications();    // Получаем все одобренные заявки
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanApplication> getAllApplications() {
        return loanApplicationDao.getAll(); // Получаем все заявки
    }

    @Transactional
    @Override
    public String processLoanApplication(Client client, BigDecimal desiredLoanAmount, Model model) {
        // Проверяем, существует ли клиент с такими паспортными данными
        Client existingClient = clientService.getClientByPassport(client.getPassportData());

        if (existingClient != null) {
            // Если клиент существует, используем его
            client = existingClient;
        } else {
            // Если клиента нет, сохраняем нового
            clientService.saveClient(client);
        }

        // Создаём новую заявку на кредит
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setClient(client);  // Устанавливаем клиента для заявки
        loanApplication.setDesiredLoanAmount(desiredLoanAmount);    // Устанавливаем желаемую сумму кредита
        applyForLoan(loanApplication);  // Применяем логику для принятия решения по заявке
        saveLoanApplication(loanApplication);   // Сохраняем заявку в базе данных

        // Создаём черновик договора
        LoanContract loanContract = new LoanContract();
        loanContract.setLoanApplication(loanApplication);   // Привязываем договор к заявке
        loanContract.setContractDate(java.time.LocalDate.now());    // Устанавливаем текущую дату как дату заключения договора

        if ("Одобрен".equals(loanApplication.getStatus())) {
            loanContract.setSignatureStatus("Не подписан"); // Устанавливаем статус подписания как "Не подписан"
            loanContractService.saveContract(loanContract); // Сохраняем договор
            model.addAttribute("loanContract", loanContract);   // Добавляем договор в модель
            return "loan-contract-sign";    // Перенаправляем на страницу для подписания договора
        } else {
            loanContract.setSignatureStatus("Отклонён");    // Если заявка отклонена, устанавливаем статус договора как "Отклонён"
            loanContractService.saveContract(loanContract); // Сохраняем договор
            model.addAttribute("loanContract", loanContract);   // Добавляем договор в модель
            return "loan-rejected"; // Перенаправляем на страницу с информацией об отклонении заявки
        }
    }
}