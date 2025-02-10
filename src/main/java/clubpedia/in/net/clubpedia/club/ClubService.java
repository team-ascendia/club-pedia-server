package clubpedia.in.net.clubpedia.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    @Autowired
    ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}
