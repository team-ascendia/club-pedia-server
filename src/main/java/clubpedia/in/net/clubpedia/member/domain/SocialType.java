package clubpedia.in.net.clubpedia.member.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum SocialType {
    KAKAO, GOOGLE, NAVER;

    @JsonCreator
    public static SocialType fromString(String value) {
        return Arrays.stream(SocialType.values())
                .filter(socialType -> socialType.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 소셜 타입입니다: " + value));
    }
}
