package clubpedia.in.net.clubpedia.event.repository;

import clubpedia.in.net.clubpedia.club.domain.QClub;
import clubpedia.in.net.clubpedia.event.domain.Event;
import clubpedia.in.net.clubpedia.event.domain.QEvent;
import clubpedia.in.net.clubpedia.genre.domain.QGenre;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Event> findEventsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Date startDate, Date endDate) {
        QClub club = QClub.club;
        QEvent event = QEvent.event;
        QGenre genre = QGenre.genre;

        JPAQuery<Event> query = queryFactory.selectFrom(event);


        // genres 필터링
        if (genres != null && !genres.isEmpty()) {
            query.where(club.genres.any().id.in(genres));
        }

        // regions 필터링
        if (regions != null && !regions.isEmpty()) {
            query.where(club.region.id.in(regions));
        }

        // 기간 쿼리 있을 경우
        if (startDate != null && endDate != null) {
            query.where(event.startDate.between(startDate, endDate)
                    .or(event.endDate.isNull().or(event.endDate.between(startDate, endDate)))
            );
        }

        // priceStart 필터링
        if (priceStart != null) {
            query.where(event.price.goe(priceStart));
        }

        // priceEnd 필터링
        if (priceEnd != null) {
            query.where(event.price.loe(priceEnd));
        }

        // 즉시 로딩
        query = query.leftJoin(event.club, club).fetchJoin()
                .leftJoin(club.genres, genre).fetchJoin()
                .leftJoin(club.region).fetchJoin()
                .distinct();

        return query.fetch();
    }
}