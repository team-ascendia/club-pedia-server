package clubpedia.in.net.clubpedia.club.dto;

import clubpedia.in.net.clubpedia.genre.dto.GenreResponse;
import clubpedia.in.net.clubpedia.region.dto.RegionResponse;
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
//    private Boolean isOpen;
//    private String summary;
    private RegionResponse region;
    private List<GenreResponse> genres;
}
