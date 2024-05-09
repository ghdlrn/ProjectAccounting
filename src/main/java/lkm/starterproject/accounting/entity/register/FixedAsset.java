package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
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
@Table(name = "fixed_asset")
public class FixedAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fixed_asset_code")
    private Long code;        //고정자산 코드번호

    @Column(nullable = false)
    private String name;        //자산 명

    private LocalDate acquisitionDate; //취득일

    private Long amount;    //취득수량

    private Long acquisitionValue;   //취득가액

    private String depreciationStatus;  //감가상각법

    private Long durableYears;   //내용연수

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UseStatus useStatus;    //사용여부

    private LocalDate endOfUseDate; //사용종료일
    
    private String note;    //비고

}