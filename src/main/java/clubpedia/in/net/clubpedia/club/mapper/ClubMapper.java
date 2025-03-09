package clubpedia.in.net.clubpedia.club.mapper;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClubMapper {
    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

    ClubResponse toDto(Club club); // 단일 변환
    List<ClubResponse> toDtoList(List<Club> clubs); // 리스트 변환
}
