package lkm.starterproject.entity;

import jakarta.persistence.*;
import lkm.starterproject.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="finance")
public class FinanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String name;    //은행(계좌)명

    @Column(nullable = false)
    private int accountNumber;  //계좌번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;    //사용구분

    private String depositType; //예금종류

    private double interestRate;    //이자율

    private long overdraftLimit; //당좌한도액

    @Temporal(TemporalType.DATE)
    private LocalDate overdraftDate;    //당좌차월기일

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;  //사업자등록번호

    private int accountTitle;   //계정과목

    private int zipCode;    //우편번호

    private String address; //주소

    private int localTaxBillCode;   //지방세법정동코드

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private String homePage;    //홈페이지주소

    @Temporal(TemporalType.DATE)
    private LocalDate accoountOpenDate; //계좌개설일

    @Temporal(TemporalType.DATE)
    private LocalDate accountCloseDate; //계좌해지일

    private String note;    //비고

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code")
    private CompanyEntity companyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finance_code")
    private FinanceEntity financeEntity;
}
