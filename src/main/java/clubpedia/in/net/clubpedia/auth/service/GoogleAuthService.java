package clubpedia.in.net.clubpedia.auth.service;

import clubpedia.in.net.clubpedia.member.domain.Member;
import clubpedia.in.net.clubpedia.member.domain.SocialType;
import clubpedia.in.net.clubpedia.member.repository.MemberRepository;
import clubpedia.in.net.clubpedia.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

import static clubpedia.in.net.clubpedia.member.service.MemberService.generateNickname;

@Service
public class GoogleAuthService extends AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;
    @Autowired
    private MemberService memberService;

    @Override
    public String getSocialAccessToken(String redirectUri, String code) {
        ResponseEntity<Map> response = requestSocialUserAccessToken(redirectUri, code);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return responseBody != null ? (String) responseBody.get("access_token") : null;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 코드이거나 만료된 코드입니다.");
        }
    }

    @Override
    public Member getMemberBySocialAccessToken(String socialAccessToken) {
        // 1. Google API로 사용자 정보 요청
        ResponseEntity<Map> response = requestSocialUserInfo(socialAccessToken);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch user info from Google API.");
        }

        Map<String, Object> responseBody = response.getBody();

        // 2. 사용자 정보 추출
        String socialId = String.valueOf(responseBody.get("sub"));
        String email = (String) responseBody.get("email");
        String name = (String) responseBody.get("name");

        if (email == null) {
            throw new RuntimeException("Invalid email information from Google.");
        }

        // 3. 기존 회원 여부 확인
        Optional<Member> existingMember = memberRepository.findBySocialId(socialId);
        if (existingMember.isPresent()) {
            return existingMember.get();
        } else {
            validateEmail(email);
            Member member = Member.builder()
                    .socialId(socialId)
                    .email(email)
                    .name(name)
                    .nickname(memberService.generateNickname())
                    .socialType(SocialType.GOOGLE)
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
        body.add("client_id", googleClientId);
        body.add("client_secret", googleClientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // POST 요청 실행
        String requestUrl = "https://oauth2.googleapis.com/token";
        return restTemplate.exchange(requestUrl, HttpMethod.POST, request, Map.class);
    }

    private ResponseEntity<Map> requestSocialUserInfo(String socialAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + socialAccessToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(headers);
        String requestUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        return restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map.class);
    }
}
