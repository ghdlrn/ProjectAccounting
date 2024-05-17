package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDto toDto(Member entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    Member toEntity(MemberDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환
}