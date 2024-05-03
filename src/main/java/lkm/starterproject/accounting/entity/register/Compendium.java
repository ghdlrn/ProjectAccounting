package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compendium")
public class Compendium { //적요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compendium_code")
    private Long code;     //적요코드

    @Column(nullable=false)
    private String name;  //적요명

    private String note;    //비고
}
