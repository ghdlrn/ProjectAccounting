package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, LocalTaxMapper.class})
public interface CustomerMapper {

    CustomerDto toDto(Customer entity);

    @Mapping(target = "company", ignore = true)
    Customer toEntity(CustomerDto dto);

    List<CustomerDto> toDtoList(List<Customer> entityList);

    @Mapping(target = "regTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateDto(CustomerDto dto, @MappingTarget Customer entity);
}
