package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.StatementTypeStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="statement")
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Temporal(TemporalType.DATE)
    private LocalDate RegistrationDate;

    @Enumerated(EnumType.STRING)
    private StatementTypeStatus statementTypeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounting_title_code")
    private AccountTitleEntity accountTitleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_code")
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compendium_code")
    private CompendiumEntity compendiumEntity;

    private int debit;private int credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code")
    private CompanyEntity companyEntity;
}
