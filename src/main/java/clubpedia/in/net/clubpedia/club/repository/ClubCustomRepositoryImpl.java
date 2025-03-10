package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.Club;
import clubpedia.in.net.clubpedia.club.domain.QClub;
import clubpedia.in.net.clubpedia.genre.domain.QGenre;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubCustomRepositoryImpl implements ClubCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Club> findClubsByFilter(
            Pageable pageable,
            String order,
            List<Long> genres,
            List<Long> regions,
            Integer priceStart,
            Integer priceEnd,
            Boolean isOpen,
            String requestTime) {
        // TODO : isOpen 값 아직 동적으로 구현되지 않음.
        // TODO : 정렬 미적용 상태

        QClub club = QClub.club;
        QGenre genre = QGenre.genre;

        // 기본 쿼리 설정
        JPAQuery<Club> query = queryFactory.selectFrom(club);

        // 장르 필터값 있을 경우
        if (genres != null && !genres.isEmpty()) {
            query.join(club.genres, genre).where(genre.id.in(genres));
        }

        // 지역 필터값 있을 경우
        if (regions != null && !regions.isEmpty()) {
            query.where(club.region.id.in(regions));
        }

        // 가격 필터값 있을 경우
        if (priceStart != null) {
            query.where(club.price.goe(priceStart));
        }
        if (priceEnd != null) {
            query.where(club.price.loe(priceEnd));
        }

        // 페이징 적용
        query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        return query.fetch();
    }
}
