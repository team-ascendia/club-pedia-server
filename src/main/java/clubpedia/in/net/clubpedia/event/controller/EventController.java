package clubpedia.in.net.clubpedia.event.controller;

import clubpedia.in.net.clubpedia.event.dto.EventResponse;
import clubpedia.in.net.clubpedia.event.service.EventService;
import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Tag(name="Event - 이벤트")
@RestController
public class EventController {
    @Autowired
    EventService eventService;

    @Operation(
            summary = "이벤트 리스트 조회",
            description = "`order` : 아직 상세페이지가 없어 인기순 지표를 반영하지 못했습니다."
    )
    @GetMapping("/events")
    public PagedResponse<EventResponse> list(
            @Positive @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "popular") String order,
            @RequestParam(required = false) List<Long> genres,
            @RequestParam(required = false) List<Long> regions,
            @RequestParam(required = false) Integer priceStart,
            @RequestParam(required = false) Integer priceEnd,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate

    ) {
        // startDate와 endDate 검증
        if ((startDate != null && endDate == null) || (startDate == null && endDate != null)) {
            throw new IllegalArgumentException("startDate와 endDate는 함께 제공되어야 합니다.");
        }

        // Response 생성
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<EventResponse> eventPage = eventService.getEventsByFilter(pageable, order, genres, regions, priceStart, priceEnd, startDate, endDate);

        return new PagedResponse<>(eventPage);
    }
}
