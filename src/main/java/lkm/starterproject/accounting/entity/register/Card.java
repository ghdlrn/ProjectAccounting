package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private String name;    //카드(사)명

    @Column(nullable = false)
    private String cardNumber;  //카드번호

    private String note;    //비고

    private String division;    //거래구분

    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용구분

    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;    //주소

    @Column(length = 12, unique = true)
    private String registrationNumber;  //사업자등록번호

    private String homePage;        //홈페이지주소

    private LocalDate contractStartDate;    //계약시작일

    private LocalDate contractEndDate;       //계약종료일

    private Double commission;      //수수료

    private LocalDate expirationStartDate;  //유효기간 시작일

    private LocalDate expirationEndDate;     //유효기간 종료일

    private String chargeName;  //담당자명

    private LocalDate accountDay;   //결제일

}
