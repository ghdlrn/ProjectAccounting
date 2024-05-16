package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "USER")    //회원가입시 권한 USER로 저장
    @Mapping(target = "memberCompanies", ignore = true)
    Member toEntity(MemberDto dto);

    MemberDto toDto(Member entity);
}