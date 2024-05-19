package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {CompanyMapper.class, AddressMapper.class, LocalTaxMapper.class})
public interface CustomerMapper {

    CustomerDto toDto(Customer entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    Customer toEntity(CustomerDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    void updateDto(CustomerDto dto, @MappingTarget Customer entity);
}