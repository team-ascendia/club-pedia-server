package clubpedia.in.net.clubpedia.controller;

import clubpedia.in.net.clubpedia.domain.Genre;
import clubpedia.in.net.clubpedia.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Genre> list() {
        return genreService.getAllGenres();
    }

}
