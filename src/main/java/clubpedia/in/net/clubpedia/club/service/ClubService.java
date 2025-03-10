package clubpedia.in.net.clubpedia.club.service;

import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.club.mapper.ClubMapper;
import clubpedia.in.net.clubpedia.club.repository.ClubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper = ClubMapper.INSTANCE;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Page<ClubResponse> getClubsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Boolean isOpen, LocalDateTime requestTime) {
        List<ClubResponse> clubResponses = clubRepository.findClubsByFilter(pageable, order, genres, regions, priceStart, priceEnd, isOpen, requestTime);
        return new PageImpl<>(clubResponses, pageable, clubResponses.size());
    }
}
