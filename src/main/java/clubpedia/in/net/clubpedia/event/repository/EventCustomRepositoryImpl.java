package clubpedia.in.net.clubpedia.event.repository;

import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import clubpedia.in.net.clubpedia.event.mapper.EventMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<EventResponse> findEventsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Date startDate, Date endDate, LocalDateTime requestTime) {
        return new ArrayList<>();
    }
}
