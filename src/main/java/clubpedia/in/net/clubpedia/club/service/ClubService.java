package clubpedia.in.net.clubpedia.club.service;

import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.club.mapper.ClubMapper;
import clubpedia.in.net.clubpedia.club.repository.ClubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper = ClubMapper.INSTANCE;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Page<ClubResponse> getAllClubs(Pageable pageable) {
        return clubRepository.findAll(pageable)
                .map(clubMapper::toDto);
    }
}
