package lkm.starterproject.accounting.entity.statement;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.MissingScheduleStatus;
import lkm.starterproject.accounting.constants.SurtaxStatus;
import lkm.starterproject.accounting.constants.SurtaxTypeStatus;
import lkm.starterproject.accounting.entity.register.Customer;
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
@Table(name = "purchase_sales_statement")
public class PurchaseSalesStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_sales_code")
    private Long code;

    private LocalDate RegistrationDate;     //등록일자

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SurtaxStatus surtaxStatus;    //부가세유형

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SurtaxTypeStatus surtaxTypeStatus;  //부가세 구분

    private String approvalNumber;  //승인번호

    @Enumerated(EnumType.STRING)
    private MissingScheduleStatus missingScheduleStatus;    //예정누락분여부

    private String name;    //품명

    private Long amount;    //수량

    private Long unitPrice; //단가

    private Long supplyVaule;   //공급가액

    private Long surtax;    //부가세

    private Long ExtendedPrice; //총액

    private String resentment;  //분개종류


}
