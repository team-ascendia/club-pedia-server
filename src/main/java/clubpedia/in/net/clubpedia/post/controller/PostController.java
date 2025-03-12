package clubpedia.in.net.clubpedia.post.controller;

import clubpedia.in.net.clubpedia.global.dto.PagedResponse;
import clubpedia.in.net.clubpedia.post.dto.PostResponse;
import clubpedia.in.net.clubpedia.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="Post - 포스트")
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Operation(
            summary = "포스트 리스트 조회",
            description = "`order` : 아직 상세페이지가 없어 인기순 지표를 반영하지 못했습니다."
    )
    @GetMapping("/posts")
    public PagedResponse<PostResponse> list(
            @Positive @RequestParam(defaultValue = "1") Integer page,
            @Positive @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "popular") String order
    ) {
        // Response 생성
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<PostResponse> postPage = postService.getAllPosts(pageable);

        return new PagedResponse<>(postPage);
    }
}
