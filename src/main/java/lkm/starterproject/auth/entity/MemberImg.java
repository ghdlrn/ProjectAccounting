package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="member_img")
public class MemberImg extends BaseEntity{

    @Id
    @Column(name = "member_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String imgName;     //이미지 파일명

    private String oriImgName;  //원본 이미지 파일 명

    private String imgUrl;  //이미지 조회경로

    private String repimgYn;    //대표 이미지 여부

    public void updateMemberImg(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }
}
