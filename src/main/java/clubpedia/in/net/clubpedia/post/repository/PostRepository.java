package clubpedia.in.net.clubpedia.post.repository;

import clubpedia.in.net.clubpedia.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
