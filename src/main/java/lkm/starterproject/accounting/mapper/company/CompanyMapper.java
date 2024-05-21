package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AddressMapper.class, TaxOfficeMapper.class, LocalTaxMapper.class })
public interface CompanyMapper {


    CompanyDto toDto(Company entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    Company toEntity(CompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CompanyDto> toDtoList(List<Company> entityList);

    void updateDto(CompanyDto dto, @MappingTarget Company entity);

}
