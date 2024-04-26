package lkm.starterproject.bulletinboard.entity;

import jakarta.persistence.*;
import lkm.starterproject.auth.entity.MemberEntity;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulletinBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;       //제목

    @Column(nullable = false, length = 1000)
    private String content;     //내용

    @OneToOne
    @JoinColumn(name = "member_code")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  //작성시간
}
