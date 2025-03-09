package clubpedia.in.net.clubpedia.auth.service;

import clubpedia.in.net.clubpedia.global.exception.CustomException;
import clubpedia.in.net.clubpedia.global.exception.ExceptionType;
import clubpedia.in.net.clubpedia.member.domain.Member;
import clubpedia.in.net.clubpedia.member.domain.SocialType;
import clubpedia.in.net.clubpedia.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class KakaoAuthService extends AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Override
    public String getSocialAccessToken(String redirectUri, String code) {
        ResponseEntity<Map> response = requestSocialUserAccessToken(redirectUri, code);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return responseBody != null ? (String) responseBody.get("access_token") : null;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 사용된 코드입니다.");
        }
    }


    @Override
    public Member getMemberBySocialAccessToken(String socialAccessToken) {
        // 1. 카카오 API로 사용자 정보 요청
        ResponseEntity<Map> response = requestSocialUserInfo(socialAccessToken);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch user info from Kakao API.");
        }

        Map<String, Object> responseBody = response.getBody();

        // 2. 사용자 정보 추출
        String socialId = String.valueOf(responseBody.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) responseBody.get("kakao_account");

        if (kakaoAccount == null || !Boolean.TRUE.equals(kakaoAccount.get("is_email_valid"))) {
            throw new RuntimeException("Invalid email information from Kakao.");
        }

        String email = (String) kakaoAccount.get("email");

        // 3. 기존 회원 여부 확인
        Optional<Member> existingMember = memberRepository.findBySocialId(socialId);
        if (existingMember.isPresent()) {
            return existingMember.get();
        } else {
            validateEmail(email);
            Member member = Member.builder()
                    .socialId(socialId)
                    .email(email)
                    .socialType(SocialType.KAKAO)
                    .build();
            return memberRepository.save(member);
        }
    }

    private ResponseEntity<Map> requestSocialUserAccessToken(String redirectUri, String code) {
        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 바디 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // POST 요청 실행
        String requestUrl = "https://kauth.kakao.com/oauth/token";
        try {
            return restTemplate.exchange(requestUrl, HttpMethod.POST, request, Map.class);
        } catch (HttpClientErrorException e) {
            throw new CustomException(ExceptionType.INVALID_CODE);
        }
    }

    private ResponseEntity<Map> requestSocialUserInfo(String socialAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + socialAccessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> request = new HttpEntity<>(headers);
        String requestUrl = "https://kapi.kakao.com/v2/user/me";

        return restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map.class);
    }
}
