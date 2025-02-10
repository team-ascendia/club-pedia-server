package clubpedia.in.net.clubpedia.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClubController {
    @Autowired
    ClubService clubService;

    @GetMapping("/clubs")
    public List<Club> list() {
        List<Club> clubs = clubService.getAllClubs();
        return clubs;
    }

}
