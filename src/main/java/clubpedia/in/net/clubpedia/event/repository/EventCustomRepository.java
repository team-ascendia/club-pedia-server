package clubpedia.in.net.clubpedia.event.repository;

import clubpedia.in.net.clubpedia.event.domain.Event;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventCustomRepository {
    List<Event> findEventsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Date startDate, Date endDate);
}
