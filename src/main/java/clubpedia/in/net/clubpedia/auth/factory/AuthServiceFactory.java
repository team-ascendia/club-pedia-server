package clubpedia.in.net.clubpedia.auth.factory;

import clubpedia.in.net.clubpedia.auth.service.AuthService;
import clubpedia.in.net.clubpedia.auth.service.GoogleAuthService;
import clubpedia.in.net.clubpedia.auth.service.KakaoAuthService;
import clubpedia.in.net.clubpedia.auth.service.NaverAuthService;
import clubpedia.in.net.clubpedia.member.domain.SocialType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthServiceFactory {

    private final Map<SocialType, AuthService> authServices;

    public AuthServiceFactory(KakaoAuthService kakaoAuthService, GoogleAuthService googleAuthService, NaverAuthService naverAuthService) {
        this.authServices = Map.of(
                SocialType.KAKAO, kakaoAuthService,
                SocialType.GOOGLE, googleAuthService,
                SocialType.NAVER, naverAuthService
        );
    }

    public AuthService getAuthService(SocialType socialType) {
        return authServices.get(socialType);
    }
}
