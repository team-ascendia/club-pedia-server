package clubpedia.in.net.clubpedia.mapper;

import clubpedia.in.net.clubpedia.domain.Region;
import clubpedia.in.net.clubpedia.dto.RegionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    RegionResponse toDto(Region region); // 단일 변환
    List<RegionResponse> toDtoList(List<Region> regions); // 리스트 변환
}
