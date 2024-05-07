package lkm.starterproject.auth.dto;

import lkm.starterproject.auth.entity.MemberImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MemberImgDto {

    private Long Id;

    private String imgName;     //이미지 파일명

    private String oriImgName;  //원본 이미지 파일 명

    private String imgUrl;  //이미지 조회경로

    private String repimgYn;    //대표 이미지 여부

    public static ModelMapper modelMapper = new ModelMapper();

    public static MemberImgDto of(MemberImg memberImg) {
        return modelMapper.map(memberImg, MemberImgDto.class);
    }
}
