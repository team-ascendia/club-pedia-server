package clubpedia.in.net.clubpedia.service;

import clubpedia.in.net.clubpedia.dto.RegionResponse;
import clubpedia.in.net.clubpedia.mapper.RegionMapper;
import clubpedia.in.net.clubpedia.repository.RegionRepository;
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
