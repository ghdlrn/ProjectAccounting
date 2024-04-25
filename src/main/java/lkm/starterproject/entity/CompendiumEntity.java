package lkm.starterproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="compendium")
public class CompendiumEntity { //적요

    @Id
    @Column(name="compendium_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;     //적요코드

    @Column(nullable=false)
    private String name;  //적요명


}
