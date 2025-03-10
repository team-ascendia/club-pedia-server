package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long>, ClubCustomRepository {
}
