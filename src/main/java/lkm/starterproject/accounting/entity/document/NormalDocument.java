package lkm.starterproject.accounting.entity.document;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Compendium;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "normal_document")
public class NormalDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "normal_document_id")
    private Long id;

    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDate date;     //일자

    private String division;    //구분

    @ManyToOne
    @JoinColumn(name = "account_title_id")
    private AccountTitle accountTitle;  //계정과목

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;  //거래처

    @ManyToOne
    @JoinColumn(name = "compendium_id")
    private Compendium compendium;  //적요

    private Long debit; //차변

    private Long credit;    //대변
}
