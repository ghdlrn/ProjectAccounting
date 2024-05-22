package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "compendium")
public class Compendium extends BaseEntity {   //적요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compendium_id")
    private Long id;        //코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_title_id")
    private AccountTitle accountTitle;

    private Long cashCode;   //현금적요 코드

    private String cashContent; //현금적요내용

    private String cashNote;    //현금적요비고

    private Long replacementCode;   //대체적요 코드

    private String replacementContent; //대체적요내용
    
    private String replacementNote;    //대체적요비고


}
