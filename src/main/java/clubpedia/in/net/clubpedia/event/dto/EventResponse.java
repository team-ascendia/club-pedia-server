package clubpedia.in.net.clubpedia.event.dto;

import clubpedia.in.net.clubpedia.club.dto.ClubSimpleResponse;
import clubpedia.in.net.clubpedia.genre.dto.GenreResponse;
import clubpedia.in.net.clubpedia.region.dto.RegionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


@Getter
@AllArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String thumbnailImageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer price;
    private String summary;
    private ClubSimpleResponse club;
    private RegionResponse region;
    private List<GenreResponse> genres;
}
