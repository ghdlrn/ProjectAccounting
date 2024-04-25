package lkm.starterproject.entity;

import jakarta.persistence.*;
import lkm.starterproject.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="finance")
public class FinanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int accountNumber;

    private String division;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;

    @Column(nullable = false)
    private int financialInstitutionCode;

    private String branch;

    private String depositType;

    private double interestRate;

    private int overdraftLimit;

    @Temporal(TemporalType.DATE)
    private LocalDate overdraftDate;

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;

    private int accountTitle;

    private int customerGroupingCode;

    private int zipCode;

    private String address;

    private int localTaxBillCode;

    @Column(length = 13)
    private String phone;

    @Column(length = 13)
    private String fax;

    private String homePage;

    @Temporal(TemporalType.DATE)
    private LocalDate accoountOpenDate;

    @Temporal(TemporalType.DATE)
    private LocalDate accountCloseDate;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code")
    private CompanyEntity companyEntity;
}
