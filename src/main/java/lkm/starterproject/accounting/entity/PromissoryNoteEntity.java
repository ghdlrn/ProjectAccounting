package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.PromissoryNoteTypeStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="promissory_note")
public class PromissoryNoteEntity {   //어음

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //코드번호

    private PromissoryNoteTypeStatus promissoryNoteTypeStatus;  //어음종류

    private LocalDate issuingDate;  //발행일

    private LocalDate expirationDate;   //만기일

    private String promissoryNoteNumber;    //어음번호

    @OneToOne
    @JoinColumn(name = "customer_code")
    private CustomerEntity customerEntity;

    private Long money;     //금액
    
    private String finance; //발행기관
    
    private Long amount;    //개수
}
