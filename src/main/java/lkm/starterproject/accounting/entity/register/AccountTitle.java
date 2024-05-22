package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "account_title")
public class AccountTitle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_title_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private Long code;  //계정과목코드

    private String name;    // 계정명

    private String balanceClassification;   //대차구분

    private String type;    //계정종류

    @Builder.Default
    @OneToMany(mappedBy = "accountTitle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compendium> compendiums = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용구분

    private String properties;  //계정속성

    private String relatedWork; //관련업무

    private String cheque;   //수표

    private String balanceTallyClassification;  //잔액집계구분

    private String incomeExpenditureClassification; //수입지출구분

    private String incomeExpenditureParentAccount;  //수입지출 상위계정

    private String financialStatementName;  //재무제표 표시명

    private String financialStatementPrintingLocation;  //재무제표인쇄위치

    private String financialStatementAmountThickness;   //재무제표금액굵기

    private String financialStatementAmountParentheses; //재무제표금액괄호

    private String financialStatementHyperlinkTarget;   //재무제표 하이퍼링크대상

    private String financialStatementParentAccount; //재무제표  상위계정

}
