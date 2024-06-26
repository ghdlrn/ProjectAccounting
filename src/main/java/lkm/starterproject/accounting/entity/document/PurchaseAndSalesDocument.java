package lkm.starterproject.accounting.entity.document;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.*;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "purchase_and_sales_document")
public class PurchaseAndSalesDocument extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_and_sales_document_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private Long code;  //개인 코드

    private String type;    //유형

    private String name;    //품명

    private Long amount;    //수량

    private Long unitPrice; //단가

    private Long valueOfSupply; //공급가액

    private Long surtax;        //부가세

    private Long total;     //합계

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;  //계좌

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;  //카드

    @ManyToOne
    @JoinColumn(name = "account_title_id")
    private AccountTitle accountTitle;  //계정과목

    @ManyToOne
    @JoinColumn(name = "compendium_id")
    private Compendium compendium;  //적요

    private Long debit; //차변

    private Long credit;    //대변
}
