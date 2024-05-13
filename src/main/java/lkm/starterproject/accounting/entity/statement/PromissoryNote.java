package lkm.starterproject.accounting.entity.statement;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promissory_note")
public class PromissoryNote {   //어음

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "promissory_note_id")
    private Long id;        //코드번호

    private String promissoryNoteTypeStatus;  //어음종류

    private LocalDate issuingDate;  //발행일

    private LocalDate expirationDate;   //만기일

    private String promissoryNoteNumber;    //어음번호

    private Long money;     //금액
    
    private String finance; //발행기관
    
    private Long amount;    //개수
}
