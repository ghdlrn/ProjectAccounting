package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MemberCompanyMapper.class)
public interface CustomerMapper {

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    CustomerDto toDto(Customer entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    Customer toEntity(CustomerDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CustomerDto> toDTOList(List<Customer> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<Customer> toEntityList(List<CustomerDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장

    void updateDto(CustomerDto dto, @MappingTarget Customer entity);
}