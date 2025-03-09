package clubpedia.in.net.clubpedia.genre.service;

import clubpedia.in.net.clubpedia.genre.dto.GenreResponse;
import clubpedia.in.net.clubpedia.genre.mapper.GenreMapper;
import clubpedia.in.net.clubpedia.genre.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper = GenreMapper.INSTANCE;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Page<GenreResponse> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable)
                .map(genreMapper::toDto);
    }
}
