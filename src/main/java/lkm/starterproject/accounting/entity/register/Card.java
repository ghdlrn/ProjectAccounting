package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.TradeStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.company.Company;
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
public class Card {

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



    private Double commission;      //수수료

    private Integer commissionAccountTitle; //수수료 계정과목

    private LocalDate accountDay;   //결제일

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;  //사업자등록번호

    private Integer accountTitle;   //계정과목

    private Integer customerGroupingCode;       //거래처분류코드

    private Integer zipCode;    //우편번호

    private String address; //주소

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private String homePage;        //홈페이지주소

    @Temporal(TemporalType.DATE)
    private LocalDate constractOpenDate;    //계약시작일

    @Temporal(TemporalType.DATE)
    private LocalDate constractCloseDate;       //계약종료일

    private String note;    //비고




}
