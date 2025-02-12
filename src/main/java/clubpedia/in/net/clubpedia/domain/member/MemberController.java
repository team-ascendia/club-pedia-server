package clubpedia.in.net.clubpedia.domain.member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Member")
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @Operation(
            summary = "카카오 로그인&회원가입",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
                    @ApiResponse(responseCode = "500", description = "서버에 예기치 못한 오류가 발생했습니다.")
            }
    )
    @GetMapping("/auth/kakao")
    public List<Member> list() {
        List<Member> members = memberService.getAllMembers();
        return members;
    }
}