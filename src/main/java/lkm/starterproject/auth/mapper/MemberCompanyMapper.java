package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberCompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "company", ignore = true)
    MemberCompanyDto toDto(MemberCompany memberCompany);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "company", ignore = true)
    MemberCompany toEntity(MemberCompanyDto memberCompanyDto);
}
