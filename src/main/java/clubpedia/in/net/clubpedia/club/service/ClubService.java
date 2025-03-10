package clubpedia.in.net.clubpedia.club.service;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.club.mapper.ClubMapper;
import clubpedia.in.net.clubpedia.club.repository.ClubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper = ClubMapper.INSTANCE;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    public Page<ClubResponse> getClubsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Boolean isOpen, String requestTime) {
        List<Club> clubs = clubRepository.findClubsByFilter(pageable, order, genres, regions, priceStart, priceEnd, isOpen, requestTime);

        // List를 ClubResponse 리스트로 변환
        List<ClubResponse> clubResponses = clubs.stream()
                .map(clubMapper::toDto)
                .collect(Collectors.toList());

        // Page 객체로 변환
        return new PageImpl<>(clubResponses, pageable, clubs.size());
    }
}
