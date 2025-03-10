package clubpedia.in.net.clubpedia.genre.controller;

import clubpedia.in.net.clubpedia.genre.dto.GenreResponse;
import clubpedia.in.net.clubpedia.genre.service.GenreService;
import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
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

@Tag(name="Genre - 장르")
@RestController
public class GenreController {
    @Autowired
    GenreService genreService;

    @Operation(
            summary = "장르 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
            }
    )
    @GetMapping("/genres")
    public PagedResponse<GenreResponse> list(
            @Positive @RequestParam(defaultValue = "1") int page,
            @Positive @RequestParam(defaultValue = "100") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by( "sequence"));
        Page<GenreResponse> genrePage = genreService.getAllGenres(pageable);
        return new PagedResponse<>(genrePage);
    }
}
