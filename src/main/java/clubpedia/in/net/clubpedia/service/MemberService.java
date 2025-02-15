package clubpedia.in.net.clubpedia.service;
import clubpedia.in.net.clubpedia.domain.Member;
import clubpedia.in.net.clubpedia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {


        return memberRepository.findAll();
    }
}
