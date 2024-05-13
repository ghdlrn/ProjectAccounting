package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
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
@Table(name = "finance")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "finance_id")
    private Long id;

    @Column(nullable = false)
    private String name;    //은행(계좌)명

    @Column(nullable = false)
    private Integer accountNumber;  //계좌번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;    //사용구분

    private String depositType; //예금종류

    private Double interestRate;    //이자율

    private Long overdraftLimit; //당좌한도액

    private LocalDate overdraftDate;    //당좌차월기일

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;  //사업자등록번호

    private Long accountTitle;   //계정과목

    private Long zipCode;    //우편번호

    private String address; //주소

    private Long localTaxBillCode;   //지방세법정동코드

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private String homePage;    //홈페이지주소

    private LocalDate accoountOpenDate; //계좌개설일

    private LocalDate accountCloseDate; //계좌해지일

    private String note;    //비고
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
}
