package clubpedia.in.net.clubpedia.service;
import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.dto.MemberActivationRequest;
import clubpedia.in.net.clubpedia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
