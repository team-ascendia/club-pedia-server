package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.domain.QClub;
import clubpedia.in.net.clubpedia.genre.domain.QGenre;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubCustomRepositoryImpl implements ClubCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Club> findAllByGenreIds(List<Long> genreIds) {
        QClub club = QClub.club;
        QGenre genre = QGenre.genre;

        return queryFactory
                .selectFrom(club)
                .join(club.genres, genre)
                .where(genre.id.in(genreIds))
                .fetch();
    }
}
