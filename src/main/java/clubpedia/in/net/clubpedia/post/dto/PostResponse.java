package clubpedia.in.net.clubpedia.post.dto;

import clubpedia.in.net.clubpedia.member.dto.MemberSimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String thumbnailImageUrl;
    private Integer visitCount;
    private Integer likeCount;
    private Integer commentCount;
    private MemberSimpleResponse member;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
