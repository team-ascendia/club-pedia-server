package clubpedia.in.net.clubpedia.controller;

import clubpedia.in.net.clubpedia.dto.ClubResponse;
import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
import clubpedia.in.net.clubpedia.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

@Tag(name="Club - 지역")
@RestController
public class ClubController {
    @Autowired
    ClubService clubService;

    @Operation(
            operationId = "지역 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
            }
    )
    @GetMapping("/clubs")
    public PagedResponse<ClubResponse> list(
            @Positive @RequestParam(defaultValue = "1") int page,
            @Positive @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "popular") String order,
            @RequestParam String genres,
            @RequestParam String regions,
            @RequestParam int priceStart,
            @RequestParam int priceEnd,
            @RequestParam boolean isOpen,
            @RequestParam(defaultValue = "2025-01-01T00:00:00.00") String requestTime
    ) {
        // 1. dto 내부 isOpen 보강하기
        // 2. 각 필터값 적용
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ClubResponse> clubPage = clubService.getAllClubs(pageable);
        return new PagedResponse<>(clubPage);
    }
}
