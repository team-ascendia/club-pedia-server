package clubpedia.in.net.clubpedia.club.mapper;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClubMapper {
    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

    @Mapping(source = "isOpen", target = "isOpen")
    ClubResponse toResponse(Club club, Boolean isOpen);
}
