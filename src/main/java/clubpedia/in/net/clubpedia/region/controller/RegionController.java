package clubpedia.in.net.clubpedia.region.controller;

import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
import clubpedia.in.net.clubpedia.region.dto.RegionResponse;
import clubpedia.in.net.clubpedia.region.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Region - 지역")
@RestController
public class RegionController {
    @Autowired
    RegionService regionService;

    @Operation(
            summary = "지역 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
            }
    )
    @GetMapping("/regions")
    public PagedResponse<RegionResponse> list(
            @Positive @RequestParam(defaultValue = "1") int page,
            @Positive @RequestParam(defaultValue = "100") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by( "sequence"));
        Page<RegionResponse> regionPage = regionService.getAllRegions(pageable);
        return new PagedResponse<>(regionPage);
    }
}
