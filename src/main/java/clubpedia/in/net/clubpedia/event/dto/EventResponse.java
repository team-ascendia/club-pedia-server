package clubpedia.in.net.clubpedia.event.dto;

import clubpedia.in.net.clubpedia.genre.dto.GenreResponse;
import clubpedia.in.net.clubpedia.region.dto.RegionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;


@Getter
@AllArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String thumbnailImageUrl;
    private Date startDate;
    private Date endDate;
    private Integer price;
    private String summary;
    private RegionResponse region;
    private List<GenreResponse> genres;
}
