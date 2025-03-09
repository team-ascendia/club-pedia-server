package clubpedia.in.net.clubpedia.auth.dto;

import clubpedia.in.net.clubpedia.member.domain.Gender;
import clubpedia.in.net.clubpedia.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String name;
    private String phoneNumber;
    private Date birthday;
    private Gender gender;
    private String email;
    private Boolean isSignup;

    public static AuthResponse fromMember(Member member, String accessToken) {
        return new AuthResponse(
                accessToken,
                member.getName(),
                member.getPhoneNumber(),
                member.getBirthday(),
                member.getGender(),
                member.getEmail(),
                member.getIsSignup()
        );
    }
}
