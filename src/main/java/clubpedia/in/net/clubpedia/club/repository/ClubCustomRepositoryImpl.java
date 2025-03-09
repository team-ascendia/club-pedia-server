package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubCustomRepositoryImpl implements ClubCustomRepository {
    @Autowired
    JPAQueryFactory queryFactory;

//    public List<Club> findAllByGenreIds(List<Integer> genreIds) {
//        return queryFactory.selectFrom(Club)
//    }
}
