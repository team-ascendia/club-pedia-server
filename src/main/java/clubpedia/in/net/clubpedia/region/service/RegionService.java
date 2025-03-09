package clubpedia.in.net.clubpedia.region.service;

import clubpedia.in.net.clubpedia.region.dto.RegionResponse;
import clubpedia.in.net.clubpedia.region.mapper.RegionMapper;
import clubpedia.in.net.clubpedia.region.repository.RegionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper = RegionMapper.INSTANCE;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Page<RegionResponse> getAllRegions(Pageable pageable) {
        return regionRepository.findAll(pageable)
                .map(regionMapper::toDto);
    }
}
