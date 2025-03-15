package ru.axiomatika.creditsystem;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loan_contract")
public class LoanContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_application_id", nullable = false)
    private LoanApplication loanApplication;

    @Column(name = "contract_date", nullable = false)
    private LocalDate contractDate;

    @Column(name = "signature_status", nullable = false)
    private String signatureStatus;

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getSignatureStatus() {
        return signatureStatus;
    }

    public void setSignatureStatus(String signatureStatus) {
        this.signatureStatus = signatureStatus;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LoanContract{" +
                "id=" + id +
                ", loanApplication=" + loanApplication +
                ", contractDate=" + contractDate +
                ", signatureStatus='" + signatureStatus + '\'' +
                '}';
    }
}
