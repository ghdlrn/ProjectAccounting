package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberCompanyMapper {

    MemberCompanyDto toDto(MemberCompany memberCompany);

    @Mapping(target = "member", source = "memberCompanyDto.member")
    @Mapping(target = "company", source = "memberCompanyDto.company")
    MemberCompany toEntity(MemberCompanyDto memberCompanyDto);
}
