package clubpedia.in.net.clubpedia.controller;

import clubpedia.in.net.clubpedia.domain.Club;
import clubpedia.in.net.clubpedia.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Club")
@RestController
public class ClubController {
    @Autowired
    ClubService clubService;

    @Operation(
            summary = "(미완성)클럽 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
                    @ApiResponse(responseCode = "500", description = "서버에 예기치 못한 오류가 발생했습니다.")
            }
    )
    @GetMapping("/clubs")
    public List<Club> list() {
        List<Club> clubs = clubService.getAllClubs();
        return clubs;
    }

}
