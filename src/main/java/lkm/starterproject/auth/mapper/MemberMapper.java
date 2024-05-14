package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        MemberCompanyMapper.class
})
public interface MemberMapper {

    MemberDto toDto(Member entity);

    @Mapping(target = "username", source = "dto.username")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "password", source = "dto.password")
    Member toEntity(MemberDto dto);
}
