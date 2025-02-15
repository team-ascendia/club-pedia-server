package clubpedia.in.net.clubpedia.controller;

import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.domain.SocialType;
import clubpedia.in.net.clubpedia.dto.AuthRequest;
import clubpedia.in.net.clubpedia.factory.AuthServiceFactory;
import clubpedia.in.net.clubpedia.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServiceFactory authServiceFactory;


    @Operation(
            summary = "소셜 로그인 & 회원가입",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 요청을 처리했습니다."),
                    @ApiResponse(responseCode = "500", description = "서버에 예기치 못한 오류가 발생했습니다.")
            }
    )
    @PostMapping("/social/{socialType}")
    public Member authenticate(
            @PathVariable("socialType") SocialType socialType,
            @RequestBody AuthRequest request) {

        // 적절한 AuthService 선택
        AuthService authService = authServiceFactory.getAuthService(socialType);

        // 로그인 or 회원가입 처리
        Member member = authService.auth(request.getRedirectUri(), request.getCode());

        // accessToken 가져오기
        String accessToken = authService.getAccessToken(member);
        System.out.println(accessToken);

        // AuthResponse 반환
//        AuthResponse response = new AuthResponse(member, accessToken);

        return member;
    }
}
