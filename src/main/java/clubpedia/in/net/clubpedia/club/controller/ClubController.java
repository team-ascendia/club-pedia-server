package clubpedia.in.net.clubpedia.club.controller;

import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
import clubpedia.in.net.clubpedia.club.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import java.util.List;

@Tag(name="Club - 클럽")
@RestController
public class ClubController {
    @Autowired
    ClubService clubService;

    @Operation(
            summary = "지역 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
            }
    )
    @GetMapping("/clubs")
    public PagedResponse<ClubResponse> list(
            @Positive @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "popular") String order,
            @RequestParam(required = false) List<Long> genres,
            @RequestParam(required = false) List<Long> regions,
            @RequestParam(required = false) Integer priceStart,
            @RequestParam(required = false) Integer priceEnd,
            @RequestParam(required = false) Boolean isOpen,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime requestTime
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ClubResponse> clubPage = clubService.getClubsByFilter(pageable, order, genres, regions, priceStart, priceEnd, isOpen, requestTime);
        return new PagedResponse<>(clubPage);
    }
}
