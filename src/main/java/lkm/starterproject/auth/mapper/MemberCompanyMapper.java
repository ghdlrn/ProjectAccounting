package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { MemberMapper.class })
public interface MemberCompanyMapper {

    MemberCompanyMapper INSTANCE = Mappers.getMapper(MemberCompanyMapper.class);

    @Mapping(source = "company", target = "company")
    @Mapping(source = "member", target = "member")
    MemberCompany toEntity(MemberCompanyDto dto);

    MemberCompanyDto toDto(MemberCompany entity);
}