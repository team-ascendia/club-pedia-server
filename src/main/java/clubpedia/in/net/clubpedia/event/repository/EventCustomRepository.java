package clubpedia.in.net.clubpedia.event.repository;

import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventCustomRepository {
    List<EventResponse> findEventsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Date startDate, Date endDate, LocalDateTime requestTime);
}
