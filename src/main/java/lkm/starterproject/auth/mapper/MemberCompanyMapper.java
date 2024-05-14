package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        CompanyMapper.class,
        MemberMapper.class
})
public interface MemberCompanyMapper {

    MemberCompanyDto toDto(MemberCompany entity);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "member", source = "dto.member")
    @Mapping(target = "company", source = "dto.company")
    MemberCompany toEntity(MemberCompanyDto dto);
}
