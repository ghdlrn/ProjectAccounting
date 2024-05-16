package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, MemberMapper.class})
public interface MemberCompanyMapper {

    @Mappings({
            @Mapping(source = "company", target = "company"),
            @Mapping(source = "member", target = "member")
    })
    MemberCompany toEntity(MemberCompanyDto dto);

    @Mappings({
            @Mapping(source = "company", target = "company"),
            @Mapping(source = "member", target = "member")
    })
    MemberCompanyDto toDto(MemberCompany entity);
}