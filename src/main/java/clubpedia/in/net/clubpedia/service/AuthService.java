package clubpedia.in.net.clubpedia.service;
import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.global.util.JwtUtil;
import clubpedia.in.net.clubpedia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public abstract class AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtUtil jwtUtil;

    public Member auth(String redirectUri, String code) {
        // 1. social Access Token 받기
        String socialAccessToken = getSocialAccessToken(redirectUri, code);

        // 2. 회원가입(이메일 유효성 검증) or 로그인
        return getMemberBySocialAccessToken(socialAccessToken);
    }

    void validateEmail(String email) {
        boolean isEmailExists = memberRepository.existsByEmail(email);
        if (isEmailExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일이 중복되었습니다.");
        }
    }

    public String getAccessToken(Member member) {
        return jwtUtil.generateToken(String.valueOf(member.getId()));
    }

    public abstract String getSocialAccessToken(String redirectUri, String code);
    public abstract Member getMemberBySocialAccessToken(String socialAccessToken);
}