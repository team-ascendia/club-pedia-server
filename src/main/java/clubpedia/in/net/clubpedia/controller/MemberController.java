package clubpedia.in.net.clubpedia.controller;
import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.dto.MemberActivationRequest;
import clubpedia.in.net.clubpedia.dto.MemberResponse;
import clubpedia.in.net.clubpedia.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="Member")
@RestController
@Validated
public class MemberController {

    @Autowired
    MemberService memberService;

    @Operation(
            summary = "소셜 계정 활성화",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
                    @ApiResponse(responseCode = "500", description = "서버에 예기치 못한 오류가 발생했습니다.")
            }
    )
    @PostMapping("/member/activation")
    public ResponseEntity<MemberResponse> activateMember(@AuthenticationPrincipal Member member, @Valid @RequestBody MemberActivationRequest request) {
        // 1. Member 정보 변경 & 상태 값 변경
        member = memberService.activateMember(member, request);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
}