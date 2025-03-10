package clubpedia.in.net.clubpedia.event.service;

import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import clubpedia.in.net.clubpedia.event.mapper.EventMapper;
import clubpedia.in.net.clubpedia.event.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Page<EventResponse> getEventsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Date startDate, Date endDate, LocalDateTime requestTime) {
        List<EventResponse> eventResponses = eventRepository.findEventsByFilter(pageable, order, genres, regions, priceStart, priceEnd, startDate, endDate, requestTime);
        return new PageImpl<>(eventResponses, pageable, eventResponses.size());
    }

    public Page<EventResponse> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(eventMapper::toDto);
    }
}
