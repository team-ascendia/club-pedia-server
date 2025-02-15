package clubpedia.in.net.clubpedia.dto;

import clubpedia.in.net.clubpedia.domain.Gender;
import clubpedia.in.net.clubpedia.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private Date birthday;
    private String phoneNumber;
    private Gender gender;
    private String email;
    private String profileImageUrl;
    private Boolean isSignup;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getBirthday(),
                member.getPhoneNumber(),
                member.getGender(),
                member.getEmail(),
                member.getProfileImageUrl(),
                member.getIsSignup()
        );
    }

}
