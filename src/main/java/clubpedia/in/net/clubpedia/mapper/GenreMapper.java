package clubpedia.in.net.clubpedia.mapper;

import clubpedia.in.net.clubpedia.domain.Genre;
import clubpedia.in.net.clubpedia.dto.GenreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreResponse toDto(Genre genre); // 단일 변환
    List<GenreResponse> toDtoList(List<Genre> genres); // 리스트 변환
}
