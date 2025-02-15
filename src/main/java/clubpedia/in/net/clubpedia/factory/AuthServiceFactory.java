package clubpedia.in.net.clubpedia.factory;

import clubpedia.in.net.clubpedia.domain.SocialType;
import clubpedia.in.net.clubpedia.service.AuthService;
import clubpedia.in.net.clubpedia.service.GoogleAuthService;
import clubpedia.in.net.clubpedia.service.KakaoAuthService;
import clubpedia.in.net.clubpedia.service.NaverAuthService;
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
