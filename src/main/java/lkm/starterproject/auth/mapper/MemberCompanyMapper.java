package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberCompanyMapper {

    MemberCompanyDto toDto(MemberCompany memberCompany);

    MemberCompany toEntity(MemberCompanyDto memberCompanyDto);
}
