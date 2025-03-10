package clubpedia.in.net.clubpedia.member.controller;
import clubpedia.in.net.clubpedia.member.domain.Member;
import clubpedia.in.net.clubpedia.member.dto.MemberActivationRequest;
import clubpedia.in.net.clubpedia.member.dto.MemberResponse;
import clubpedia.in.net.clubpedia.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="Member - 멤버")
@RestController
@Validated
public class MemberController {

    @Autowired
    MemberService memberService;

    @Operation(
            summary = "소셜 계정 활성화",
            responses = {}
    )
    @PostMapping("/member/activation")
    public ResponseEntity<MemberResponse> activateMember(@AuthenticationPrincipal Member member, @Valid @RequestBody MemberActivationRequest request) {
        member = memberService.activateMember(member, request);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
}