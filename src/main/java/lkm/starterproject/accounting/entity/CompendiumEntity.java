package lkm.starterproject.accounting.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="compendium")
public class CompendiumEntity { //적요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;     //적요코드

    @Column(nullable=false)
    private String name;  //적요명

    private String note;    //비고
}
