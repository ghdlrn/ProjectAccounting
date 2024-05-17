package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = MemberCompanyMapper.class)
public interface CompanyMapper {

    @Mapping(source = "memberCompanies", target = "memberCompanies")
    CompanyDto toDto(Company entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "memberCompanies", target = "memberCompanies")
    Company toEntity(CompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CompanyDto> toDTOList(List<Company> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<Company> toEntityList(List<CompanyDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장

    void updateDto(CompanyDto dto, @MappingTarget Company entity);
}
