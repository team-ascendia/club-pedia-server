package clubpedia.in.net.clubpedia.club.mapper;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClubMapper {
    ClubMapper INSTANCE = Mappers.getMapper(ClubMapper.class);

    @Mapping(source = "club.id", target = "id")
    @Mapping(source = "club.title", target = "title")
    @Mapping(source = "club.thumbnailImageUrl", target = "thumbnailImageUrl")
    @Mapping(source = "club.address", target = "address")
    @Mapping(source = "club.price", target = "price")
    @Mapping(source = "isOpen", target = "isOpen") // 동적 값 매핑
    @Mapping(source = "club.summary", target = "summary")
    @Mapping(source = "club.region", target = "region")
    @Mapping(source = "club.genres", target = "genres")
    ClubResponse toResponse(Club club, Boolean isOpen);
}
