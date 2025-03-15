package clubpedia.in.net.clubpedia.member.service;
import clubpedia.in.net.clubpedia.member.domain.Member;
import clubpedia.in.net.clubpedia.member.dto.MemberActivationRequest;
import clubpedia.in.net.clubpedia.member.repository.MemberRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member activateMember(Member member, MemberActivationRequest request) {
        member.activateMember(
                request.getName(),
                request.getPhoneNumber(),
                request.getBirthday(),
                request.getEmail(),
                request.getGender(),
                request.getIsOver14Agreed(),
                request.getIsServiceTermAgreed(),
                request.getIsPrivacyPolicyAgreed(),
                request.getIsLocationTermAgreed(),
                request.getIsMarketingAgreed()
        );
        return memberRepository.save(member);
    }

    public String generateNickname() {
        Faker faker = new Faker(new Locale("ko"));

        while (true) {
            String color = faker.color().name();
            String city = faker.address().streetName();
            String nickname = color + city;

            if (memberRepository.existsByNickname(nickname)) {
                continue;
            }

            return nickname;
        }
    }
}
