package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",
        uses = {CustomerMapper.class, TaxOfficeMapper.class,
                LocalTaxMapper.class, AddressMapper.class,
                MemberCompanyMapper.class})
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "taxOffice", source = "entity.taxOffice")
    @Mapping(target = "localTax", source = "entity.localTax")
    @Mapping(target = "address", source = "entity.address")
    CompanyDto toDto(Company entity);


    @Mapping(target = "taxOffice", source = "dto.taxOffice")
    @Mapping(target = "localTax", source = "dto.localTax")
    @Mapping(target = "address", source = "dto.address")
    Company toEntity(CompanyDto dto);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntityFromDto(CompanyDto dto, @MappingTarget Company entity);
}
