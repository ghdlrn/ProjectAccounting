package lkm.starterproject.auth.dto;

import jakarta.persistence.*;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.entity.MemberCompany;
import lkm.starterproject.auth.entity.MemberImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberInfoDto {

    private Long id;

    private String username;

    private String password;

    private String email;
    //회원 저장 후 수정할 때 이미지 정보 저장리스트
    private List<MemberImgDto> memberImgDtoList = new ArrayList<>();
    //회원 이미지 아이디 저장 리스트(수정시 이미지 아이디 담아둘 용도로 사용)
    private List<Long> memberImgIdList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();
    //modelMapper를 이용해 엔티티와 dto객체간 데이터를 복사하여 복사한 객체 반환
    public Member createMember() {
        return modelMapper.map(this, Member.class);
    }

    public static MemberInfoDto of(Member member) {
        return modelMapper.map(member, MemberInfoDto.class);
    }


}
