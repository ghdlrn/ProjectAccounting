package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { CompanyMapper.class, LocalTaxMapper.class })
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "unitReportingCustomerCode", target = "unitReportingCustomerCode")
    @Mapping(source = "localTax", target = "localTax")
    @Mapping(source = "company", target = "company")
    CustomerDto toDto(Customer entity);

    @Mapping(source = "unitReportingCustomerCode", target = "unitReportingCustomerCode")
    @Mapping(source = "localTax", target = "localTax")
    @Mapping(source = "company", target = "company")
    Customer toEntity(CustomerDto dto);

    void updateEntityFromDto(CustomerDto dto, @MappingTarget Customer entity);
}