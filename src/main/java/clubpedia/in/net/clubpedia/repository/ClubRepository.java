package clubpedia.in.net.clubpedia.repository;

import clubpedia.in.net.clubpedia.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
