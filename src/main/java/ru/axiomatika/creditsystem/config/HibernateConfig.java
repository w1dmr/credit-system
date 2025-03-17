package ru.axiomatika.creditsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {
        // Конфигурация для подключения к PostgreSQL
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/loan_management");  // URL базы данных
        dataSource.setUsername("postgres"); // Логин
        dataSource.setPassword("0000"); // Пароль
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(@Autowired DataSource dataSource) {
        // Настройка фабрики сессий Hibernate
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.axiomatika.creditsystem.entity");  // Пакет с сущностями
        sessionFactory.setHibernateProperties(hibernateProperties());   // Установка свойств Hibernate
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        // Настройки Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // Диалект для PostgreSQL
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Автоматическое обновление схемы
        properties.setProperty("hibernate.show_sql", "true");   // Показ SQL запросов в логе
        return properties;
    }
}