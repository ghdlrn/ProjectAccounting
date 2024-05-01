package lkm.starterproject.bulletinboard.entity;

import jakarta.persistence.*;
import lkm.starterproject.auth.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author; // MemberEntity를 참조

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  //작성시간
}
