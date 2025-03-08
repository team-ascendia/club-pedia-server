package clubpedia.in.net.clubpedia.repository;

import clubpedia.in.net.clubpedia.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> { }
