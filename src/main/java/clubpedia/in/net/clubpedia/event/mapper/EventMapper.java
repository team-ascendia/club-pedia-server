package clubpedia.in.net.clubpedia.event.mapper;

import clubpedia.in.net.clubpedia.event.domain.Event;
import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import clubpedia.in.net.clubpedia.genre.domain.Genre;
import clubpedia.in.net.clubpedia.region.domain.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);


    EventResponse toDto(Event event); // 단일 변환
    List<EventResponse> toDtoList(List<Event> events); // 리스트 변환
//    @Mapping(source = "region", target = "region")
//    @Mapping(source = "genres", target = "genres")
//    EventResponse toResponse(Event event, Region region, List<Genre> genres);
}
