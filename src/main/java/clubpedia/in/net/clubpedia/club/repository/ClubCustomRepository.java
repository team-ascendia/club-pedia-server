package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import java.util.List;

public interface ClubCustomRepository {
    List<Club> findAllByGenreIds(List<Long> genreIds);
}
