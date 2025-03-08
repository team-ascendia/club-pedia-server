package clubpedia.in.net.clubpedia.dto;

import clubpedia.in.net.clubpedia.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class GenreResponse {
    private Long id;
    private String title;
    
    public static GenreResponse from(Genre genre) {
        return new GenreResponse(
                genre.getId(),
                genre.getTitle()
        );
    }

}
