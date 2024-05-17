package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MemberCompanyMapper.class)
public interface MemberMapper {

    @Mapping(source = "memberCompanies", target = "memberCompanies")
    MemberDto toDto(Member entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    @Mapping(source = "memberCompanies", target = "memberCompanies")
    Member toEntity(MemberDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환
    
    List<MemberDto> toDTOList(List<Member> entityList);    //엔티티리스트 -> DTO리스트 여러 엔티티를 한번에 변환해 클라이언트에게 전달

    List<Member> toEntityList(List<MemberDto> dtoList);  //DTO리스트 -> 엔티티리스트, 여러 DTO 객체를 한번에 변환하여 DB저장
}