package clubpedia.in.net.clubpedia.post.mapper;

import clubpedia.in.net.clubpedia.post.domain.Post;
import clubpedia.in.net.clubpedia.post.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostResponse toDto(Post post);

    List<PostResponse > toDtoList(List<Post> posts);
}
