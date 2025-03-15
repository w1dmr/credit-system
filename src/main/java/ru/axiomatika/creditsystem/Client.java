package ru.axiomatika.creditsystem;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "passport_data", nullable = false, unique = true)
    private String passportData;

    @Column(name = "marital_status")
    private String maritalStatus;

    private String address;

    private String phone;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "employment_period")
    private Integer employmentPeriod;

    @Column(name = "desired_loan_amount", precision = 19, scale = 2)
    private BigDecimal desiredLoanAmount;

    // Геттеры и сеттеры
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getEmploymentPeriod() {
        return employmentPeriod;
    }

    public void setEmploymentPeriod(Integer employmentPeriod) {
        this.employmentPeriod = employmentPeriod;
    }

    public BigDecimal getDesiredLoanAmount() {
        return desiredLoanAmount;
    }

    public void setDesiredLoanAmount(BigDecimal desiredLoanAmount) {
        this.desiredLoanAmount = desiredLoanAmount;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportData='" + passportData + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", employmentPeriod=" + employmentPeriod +
                ", desiredLoanAmount=" + desiredLoanAmount +
                '}';
    }
}