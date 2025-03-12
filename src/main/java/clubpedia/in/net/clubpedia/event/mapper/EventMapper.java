package clubpedia.in.net.clubpedia.event.mapper;

import clubpedia.in.net.clubpedia.event.domain.Event;
import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "event.club.region", target = "region")
    @Mapping(source = "event.club.genres", target = "genres")
    EventResponse toDto(Event event);

    List<EventResponse> toDtoList(List<Event> events);
}
