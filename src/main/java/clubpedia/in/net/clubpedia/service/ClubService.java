package clubpedia.in.net.clubpedia.service;

import clubpedia.in.net.clubpedia.domain.Club;
import clubpedia.in.net.clubpedia.repository.ClubRepository;
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
