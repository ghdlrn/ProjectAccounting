package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "compendium")
public class Compendium {   //적요

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "compendium_id")
    private Long id;        //코드

    private String content; //적요내용
    
    private String note;    //비고


}
