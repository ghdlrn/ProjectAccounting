package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberCompanyMapper {

    MemberCompanyDto toDto(MemberCompany entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    MemberCompany toEntity(MemberCompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환
}