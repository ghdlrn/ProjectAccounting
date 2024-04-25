package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.DepreciationStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fixed_asset")
public class FixedAssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //고정자산 코드번호

    @Column(nullable = false)
    private String name;        //자산 명

    @OneToOne
    @JoinColumn(name = "account_title_code")
    private AccountTitleEntity accountTitleEntity;

    @OneToOne
    @JoinColumn(name = "car_code")
    private CarEntity carEntity;

    private LocalDate acquisitionDate; //취득일

    private Long amount;    //취득수량

    private Long acquisitionValue;   //취득가액

    private DepreciationStatus depreciationStatus;  //감가상각법

    private Integer durableYears;   //내용연수

    private UseStatus useStatus;    //사용여부

    private LocalDate endOfUseDate; //사용종료일

    @OneToOne
    @JoinColumn(name = "department_code")
    private DepartmentEntity departmentEntity;    //관리부서
    
    private String note;    //비고
}