# АРМ менеджера по оформлению кредита

## Описание веб-прибложения

Приложение представляет собой автоматизированное рабочее место (АРМ) менеджера по оформлению кредита, разработанное с использованием технологий Spring MVC, Hibernate и PostgreSQL.
Оно реализует процесс оформления кредита, включающий создание заявки с данными клиента, принятие решения по кредиту (случайное для простоты) и подписание кредитного договора.
Дополнительно система позволяет просматривать список клиентов, искать клиента по телефону, ФИО или паспорту, а также отображать списки одобренных заявок и подписанных договоров.
Интерфейс ориентирован на функциональность, без строгих требований к стилям оформления.

## Запуск приложения

1) Создать базу данных в PostgreSQL.
2) Воссоздать структуру базы данных при помощи бэкап-файла `loan-management-backup`.
3) Логин, пароль и название базы данных можно задать в конфигурационном файле `HibernateConfig.java`.
```java
dataSource.setUrl("jdbc:postgresql://localhost:5432/database_name");  // URL базы данных
dataSource.setUsername("your_login"); // Логин
dataSource.setPassword("your_password"); // Пароль
```
4) Запустить приложение и перейти по адресу http://localhost:8080/ в браузере.

## Структура каталогов

```
src/
├── main/
│   ├── java/
│   │   └── ru.axiomatika.creditsystem/
│   │       ├── config/
│   │       │   └── HibernateConfig.java                  # Конфигурация Hibernate для работы с базой данных
│   │       ├── controller/
│   │       │   ├── ClientController.java                 # Контроллер для отображения списка всех клиентов
│   │       │   ├── HomeController.java                   # Главный контроллер для обработки запросов на главную страницу
│   │       │   ├── LoanApplicationController.java        # Контроллер для работы с заявками на кредит
│   │       │   ├── LoanApplicationsController.java       # Контроллер для отображения списка всех одобренных заявок
│   │       │   ├── LoanContractController.java           # Контроллер для оформления кредитного договора
│   │       │   ├── LoanContractsController.java          # Контроллер для отображения подписанных договоров
│   │       ├── dao/
│   │       │   ├── AbstractDao.java                      # Базовый DAO класс с общими CRUD-операциями
│   │       │   ├── ClientDao.java                        # DAO для операций с клиентами
│   │       │   ├── LoanApplicationDao.java               # DAO для операций с заявками на кредит
│   │       │   ├── LoanContractDao.java                  # DAO для операций с кредитными договорами
│   │       ├── entity/
│   │       │   ├── Client.java                           # Сущность клиента
│   │       │   ├── LoanApplication.java                  # Сущность заявки на кредит
│   │       │   ├── LoanContract.java                     # Сущность кредитного договора
│   │       ├── service/
│   │       │   ├── impl/
│   │       │   │   ├── ClientServiceImpl.java            # Реализация сервиса для клиентов
│   │       │   │   ├── LoanApplicationServiceImpl.java   # Реализация сервиса для заявок на кредит
│   │       │   │   ├── LoanContractServiceImpl.java      # Реализация сервиса для кредитных договоров
│   │       │   ├── ClientService.java                    # Интерфейс сервиса для клиентов
│   │       │   ├── LoanApplicationService.java           # Интерфейс сервиса для заявок на кредит
│   │       │   ├── LoanContractService.java              # Интерфейс сервиса для кредитных договоров
│   │       └── CreditSystemApplication.java              # Главный класс Spring Boot приложения
├── resources/
    ├── templates/                                        # Шаблоны HTML страниц для веб-интерфейса
    │   ├── clients.html                                  # Страница списка клиентов
    │   ├── contract-signed.html                          # Страница для подписанного договора
    │   ├── error.html                                    # Страница ошибки
    │   ├── index.html                                    # Главная страница
    │   ├── loan-application-form.html                    # Форма заявки на кредит
    │   ├── loan-application-result.html                  # Результат подачи заявки на кредит
    │   ├── loan-applications.html                        # Страница списка одобренных заявок на кредит
    │   ├── loan-contract-sign.html                       # Страница для подписания кредитного договора
    │   ├── loan-contracts.html                           # Страница списка подписанных кредитных договоров
    │   ├── loan-rejected.html                            # Страница отклонённых заявок
    └── application.properties                            # Конфигурационный файл приложения
```
