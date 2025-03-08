package clubpedia.in.net.clubpedia.dto;

import clubpedia.in.net.clubpedia.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ClubResponse {
    private Long id;
    private String title;
    private String thumbnailImageUrl;
    private String address;
    private Integer price;
    private Boolean isOpen;
    private String summary;
    private RegionResponse region;
    private List<GenreResponse> genres;
}
