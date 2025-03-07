package clubpedia.in.net.clubpedia.service;

import clubpedia.in.net.clubpedia.domain.Genre;
import clubpedia.in.net.clubpedia.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
