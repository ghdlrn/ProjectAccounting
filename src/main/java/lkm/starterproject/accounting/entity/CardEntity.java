package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.TradeStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    @Column(nullable = false)
    private String name;    //카드(사)명

    private String cardNumber;  //카드번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeStatus tradeStatus;    //거래구분

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;    //사용구분

    @OneToMany(mappedBy = "finance", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<FinanceEntity> finances = new ArrayList<>();       //결제 계좌

    private double commission;

    private int commissionAccountTitle;

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;

    private int accountTitle;

    private int customerGroupingCode;

    private int zipCode;

    private String address;

    @Column(length = 13)
    private String phone;

    @Column(length = 13)
    private String fax;

    private String homePage;

    @Temporal(TemporalType.DATE)
    private LocalDate constractOpenDate;

    @Temporal(TemporalType.DATE)
    private LocalDate constractCloseDate;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code")
    private CompanyEntity companyEntity;


}
