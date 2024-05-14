package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "memberCompanies", ignore = true)
    Member toEntity(MemberDto dto);

    MemberDto toDto(Member entity);
}