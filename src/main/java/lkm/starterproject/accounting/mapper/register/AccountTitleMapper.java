package lkm.starterproject.accounting.mapper.register;

import lkm.starterproject.accounting.dto.register.AccountTitleDto;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.auth.mapper.MemberCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MemberCompanyMapper.class})
public interface AccountTitleMapper {

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    AccountTitleDto toDto(AccountTitle entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "company.memberCompanies", target = "company.memberCompanies")
    AccountTitle toEntity(AccountTitleDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<AccountTitleDto> toDTOList(List<AccountTitle> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<AccountTitle> toEntityList(List<AccountTitleDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장

    void updateDto(AccountTitleDto dto, @MappingTarget AccountTitle entity);
}
