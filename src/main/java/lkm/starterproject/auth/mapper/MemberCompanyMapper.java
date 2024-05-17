package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MemberMapper.class, CompanyMapper.class})
public interface MemberCompanyMapper {

    @Mapping(source = "member", target = "member")
    @Mapping(source = "company", target = "company")
    MemberCompanyDto toDto(MemberCompany entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "member", target = "member")
    @Mapping(source = "company", target = "company")
    MemberCompany toEntity(MemberCompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<MemberCompanyDto> toDTOList(List<MemberCompany> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<MemberCompany> toEntityList(List<MemberCompanyDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장
}