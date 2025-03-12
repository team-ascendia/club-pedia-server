package clubpedia.in.net.clubpedia.post.service;

import clubpedia.in.net.clubpedia.post.dto.PostResponse;
import clubpedia.in.net.clubpedia.post.mapper.PostMapper;
import clubpedia.in.net.clubpedia.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper = PostMapper.INSTANCE;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Page<PostResponse> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDto);
    }
}
