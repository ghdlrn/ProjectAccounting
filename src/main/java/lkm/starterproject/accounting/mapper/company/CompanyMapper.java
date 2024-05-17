package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import lkm.starterproject.accounting.mapper.register.CardMapper;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.accounting.mapper.register.FinanceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring",
        uses = {CustomerMapper.class, FinanceMapper.class, CardMapper.class,
                TaxOfficeMapper.class, LocalTaxMapper.class, AddressMapper.class,
        })
public interface CompanyMapper {

    @Mappings({
            @Mapping(target = "taxOffice", source = "taxOffice"),
            @Mapping(target = "localTax", source = "localTax"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "memberCompanies", source = "memberCompanies"),
    })
    CompanyDto toDto(Company entity);


    @Mappings({
            @Mapping(target = "taxOffice", source = "dto.taxOffice"),
            @Mapping(target = "localTax", source = "dto.localTax"),
            @Mapping(target = "address", source = "dto.address"),
            @Mapping(target = "memberCompanies", source = "dto.memberCompanies"),
    })
    Company toEntity(CompanyDto dto);

    @Mappings({
            @Mapping(target = "taxOffice", source = "dto.taxOffice"),
            @Mapping(target = "localTax", source = "dto.localTax"),
            @Mapping(target = "address", source = "dto.address"),
            @Mapping(target = "memberCompanies", source = "dto.memberCompanies"),
            @Mapping(target = "regTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
    })
    void updateDto(CompanyDto dto, @MappingTarget Company entity);
}
