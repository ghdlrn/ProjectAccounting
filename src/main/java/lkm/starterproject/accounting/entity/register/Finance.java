package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finance")
public class Finance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private String name;    //은행(계좌)명

    @Column(nullable = false)
    private String accountNumber;  //계좌번호

    private String note;    //비고

    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용구분

    private String depositType; //예금종류

    private Double interestRate;    //이자율

    private Long overdraftLimit; //당좌한도액

    private LocalDate overdraftDate;    //당좌차월기일

    @Column(nullable = true)
    private String businessRegistrationNumber;  //사업자등록번호

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;    //우편번호

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private String homePage;    //홈페이지주소

    private LocalDate accountOpenDate; //계좌개설일

    private LocalDate accountCloseDate; //계좌해지일

    @ManyToOne
    @JoinColumn(name = "local_tax_id")
    private LocalTax localTax;          //지방세 법정동
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
}
