package clubpedia.in.net.clubpedia.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    @Column(name="email")
    private String email;

    @Column(name="profile_image_url")
    private String profileImageUrl;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column(name="is_over_14_agreed")
    private Boolean isOver14Agreed;

    @Column(name="is_service_term_agreed")
    private Boolean isServiceTermAgreed;

    @Column(name="is_privacy_policy_agreed")
    private Boolean isPrivacyPolicyAgreed;

    @Column(name="is_location_term_agreed")
    private Boolean isLocationTermAgreed;
}
