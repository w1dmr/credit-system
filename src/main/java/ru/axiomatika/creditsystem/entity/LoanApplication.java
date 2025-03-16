package ru.axiomatika.creditsystem.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_application")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private String status;

    @Column(name = "approved_term_months")
    private Integer approvedTermMonths;

    @Column(name = "approved_amount", precision = 19, scale = 2)
    private BigDecimal approvedAmount;

    @Column(name = "desired_loan_amount", precision = 19, scale = 2)
    private BigDecimal desiredLoanAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovedTermMonths() {
        return approvedTermMonths;
    }

    public void setApprovedTermMonths(Integer approvedTermMonths) {
        this.approvedTermMonths = approvedTermMonths;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public BigDecimal getDesiredLoanAmount() {
        return desiredLoanAmount;
    }

    public void setDesiredLoanAmount(BigDecimal desiredLoanAmount) {
        this.desiredLoanAmount = desiredLoanAmount;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", client=" + client +
                ", status='" + status + '\'' +
                ", approvedTermMonths=" + approvedTermMonths +
                ", approvedAmount=" + approvedAmount +
                ", desiredLoanAmount=" + desiredLoanAmount +
                '}';
    }
}