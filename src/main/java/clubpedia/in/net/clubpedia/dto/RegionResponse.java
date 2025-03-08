package clubpedia.in.net.clubpedia.dto;

import clubpedia.in.net.clubpedia.domain.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RegionResponse {
    private Long id;
    private String title;
    
    public static RegionResponse from(Region region) {
        return new RegionResponse(
                region.getId(),
                region.getTitle()
        );
    }

}
