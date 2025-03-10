package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ClubCustomRepository {
    List<ClubResponse> findClubsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Boolean isOpen, LocalDateTime requestTime);
}
