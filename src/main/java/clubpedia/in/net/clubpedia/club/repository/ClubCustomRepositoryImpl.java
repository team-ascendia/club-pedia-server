package clubpedia.in.net.clubpedia.club.repository;

import clubpedia.in.net.clubpedia.club.domain.QClub;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.club.mapper.ClubMapper;
import clubpedia.in.net.clubpedia.genre.domain.QGenre;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import clubpedia.in.net.clubpedia.club.domain.QClubOperatingTime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class ClubCustomRepositoryImpl implements ClubCustomRepository {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private ClubMapper clubMapper;

    @Override
    public List<ClubResponse> findClubsByFilter(Pageable pageable, String order, List<Long> genres, List<Long> regions, Integer priceStart, Integer priceEnd, Boolean isOpen, LocalDateTime requestTime) {
        QClub club = QClub.club;
        QGenre genre = QGenre.genre;
        QClubOperatingTime operatingTime = QClubOperatingTime.clubOperatingTime;

        DayOfWeek requestDay = requestTime.getDayOfWeek();
        LocalTime requestLocalTime = requestTime.toLocalTime();

        // `Club` 리스트를 한 번의 쿼리로 가져오기 (isOpen 포함)
        List<Tuple> results = queryFactory.select(
                        club,
                        new CaseBuilder()
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.openTime.loe(requestLocalTime))
                                        .and(operatingTime.closeTime.goe(requestLocalTime)))
                                .then(true)
                                .otherwise(false)
                )
                .from(club)
                .leftJoin(operatingTime).on(club.id.eq(operatingTime.club.id)
                        .and(operatingTime.dayOfWeek.eq(requestDay))
                )
                // 필터 적용
                .where(
                        (genres != null && !genres.isEmpty()) ? club.genres.any().id.in(genres) : null,
                        (regions != null && !regions.isEmpty()) ? club.region.id.in(regions) : null,
                        (priceStart != null) ? club.price.goe(priceStart) : null,
                        (priceEnd != null) ? club.price.loe(priceEnd) : null,
                        (isOpen != null && isOpen) ? operatingTime.openTime.loe(requestLocalTime)
                                .and(operatingTime.closeTime.goe(requestLocalTime)) : null
                )
                // 페이징 적용 후 fetch() 실행
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Mapper를 이용해 변환
        return results.stream()
                .map(tuple -> clubMapper.toResponse(
                        tuple.get(club),
                        tuple.get(1, Boolean.class)
                ))
                .toList();
    }
}
