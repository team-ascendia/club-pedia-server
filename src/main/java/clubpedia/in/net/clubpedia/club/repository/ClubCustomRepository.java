package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClubCustomRepository {
    List<Club> findClubsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Boolean isOpen, String requestTime);
}
