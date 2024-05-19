package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.FinanceDto;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {CompanyMapper.class, AddressMapper.class, LocalTaxMapper.class})
public interface FinanceMapper {

    FinanceDto toDto(Finance entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    Finance toEntity(FinanceDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    void updateDto(FinanceDto dto, @MappingTarget Finance entity);
}
