package clubpedia.in.net.clubpedia.event.repository;

import clubpedia.in.net.clubpedia.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventCustomRepository {
}
