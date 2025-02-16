package clubpedia.in.net.clubpedia.service;

import clubpedia.in.net.clubpedia.domain.Gender;
import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.domain.SocialType;
import clubpedia.in.net.clubpedia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class NaverAuthService extends AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${naver.client-id}")
    private String naverClientId;

    @Value("${naver.client-secret}")
    private String naverClientSecret;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Naver API로 액세스 토큰 요청
     */
    @Override
    public String getSocialAccessToken(String redirectUri, String code) {
        ResponseEntity<Map> response = requestSocialUserAccessToken(redirectUri, code);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return responseBody != null ? (String) responseBody.get("access_token") : null;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Naver 로그인 실패: 잘못된 코드 사용.");
        }
    }

    /**
     * 액세스 토큰으로 Naver 회원 정보 요청
     */
    @Override
    public Member getMemberBySocialAccessToken(String socialAccessToken) {
        ResponseEntity<Map> response = requestSocialUserInfo(socialAccessToken);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Naver API에서 사용자 정보를 가져오지 못했습니다.");
        }

        Map<String, Object> responseBody = response.getBody();
        if (!responseBody.containsKey("response")) {
            throw new RuntimeException("Naver API 응답에 사용자 정보가 없습니다.");
        }

        Map<String, Object> userInfo = (Map<String, Object>) responseBody.get("response");

        // 사용자 정보 추출
        String socialId = (String) userInfo.get("id");
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String phoneNumber = (String) userInfo.get("mobile");
        Date birthday = parseBirthday((String) userInfo.get("birthyear"), (String) userInfo.get("birthday"));
        Gender gender = Gender.fromString((String) userInfo.get("gender"));  // ✅ 변환 적용

        // 기존 회원 여부 확인
        Optional<Member> existingMember = memberRepository.findBySocialId(socialId);
        if (existingMember.isPresent()) {
            return existingMember.get();
        } else {
            validateEmail(email);
            Member member = Member.builder()
                    .socialId(socialId)
                    .email(email)
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .birthday(birthday)
                    .gender(gender)
                    .socialType(SocialType.NAVER)
                    .build();
            return memberRepository.save(member);
        }
    }


    /**
     * Naver Access Token 요청
     */
    private ResponseEntity<Map> requestSocialUserAccessToken(String redirectUri, String code) {
        String requestUrl = "https://nid.naver.com/oauth2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", naverClientId);
        body.add("client_secret", naverClientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        return restTemplate.exchange(requestUrl, HttpMethod.POST, request, Map.class);
    }

    /**
     * Naver 사용자 정보 요청
     */
    private ResponseEntity<Map> requestSocialUserInfo(String socialAccessToken) {
        String requestUrl = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + socialAccessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map.class);
    }

    /**
     * "yyyy-MM-dd" 형식의 Date 변환 메서드
     */
    private Date parseBirthday(String birthYear, String birthday) {
        if (birthYear == null || birthday == null) return null;
        try {
            return DATE_FORMAT.parse(birthYear + "-" + birthday);
        } catch (ParseException e) {
            throw new RuntimeException("생년월일 변환 오류: " + birthYear + "-" + birthday);
        }
    }
}
