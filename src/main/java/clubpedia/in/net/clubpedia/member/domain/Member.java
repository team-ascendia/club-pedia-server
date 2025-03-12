package clubpedia.in.net.clubpedia.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder.Default
    @Column(name = "is_signup", nullable = false)
    @ColumnDefault("false")
    private Boolean isSignup = false;

    @Builder.Default
    @Column(name = "is_over_14_agreed", nullable = false)
    @ColumnDefault("false")
    private Boolean isOver14Agreed = false;

    @Builder.Default
    @Column(name = "is_service_term_agreed", nullable = false)
    @ColumnDefault("false")
    private Boolean isServiceTermAgreed = false;

    @Builder.Default
    @Column(name = "is_privacy_policy_agreed", nullable = false)
    @ColumnDefault("false")
    private Boolean isPrivacyPolicyAgreed = false;

    @Builder.Default
    @Column(name = "is_location_term_agreed", nullable = false)
    @ColumnDefault("false")
    private Boolean isLocationTermAgreed = false;

    @Builder.Default
    @Column(name = "is_marketing_agreed", nullable = false)
    @ColumnDefault("false")
    private Boolean isMarketingAgreed = false;

    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_id")
    private String socialId;

    public void activateMember(String name, String phoneNumber, Date birthday, String email, Gender gender, Boolean isOver14Agreed, Boolean isServiceTermAgreed, Boolean isPrivacyPolicyAgreed, Boolean isLocationTermAgreed, Boolean isMarketingAgreed) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.isOver14Agreed = isOver14Agreed;
        this.isServiceTermAgreed = isServiceTermAgreed;
        this.isPrivacyPolicyAgreed = isPrivacyPolicyAgreed;
        this.isLocationTermAgreed = isLocationTermAgreed;
        this.isMarketingAgreed = isMarketingAgreed;
        if (isOver14Agreed && isServiceTermAgreed && isPrivacyPolicyAgreed && isLocationTermAgreed) {
            this.isSignup = true;
        }
    }
}
