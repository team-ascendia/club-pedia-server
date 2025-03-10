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


import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import clubpedia.in.net.clubpedia.club.domain.QClub;
import clubpedia.in.net.clubpedia.club.dto.ClubResponse;
import clubpedia.in.net.clubpedia.club.mapper.ClubMapper;
import clubpedia.in.net.clubpedia.genre.domain.QGenre;
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
        DayOfWeek previousDay = requestDay == DayOfWeek.MONDAY ? DayOfWeek.SUNDAY : DayOfWeek.of(requestDay.getValue() - 1); // 하루 전 요일
        LocalTime requestLocalTime = requestTime.toLocalTime();

        List<Tuple> results = queryFactory.select(
                        club,
                        new CaseBuilder()
                                // 1️⃣ 기본 조건: openTime ≤ 현재 시간 ≤ closeTime (현재 요일)
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.dayOfWeek.eq(requestDay))
                                        .and(operatingTime.openTime.loe(requestLocalTime))
                                        .and(operatingTime.closeTime.goe(requestLocalTime)))
                                .then(true)

                                // 2️⃣ "하루를 넘기는 운영 시간" (현재 요일)
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.dayOfWeek.eq(requestDay))
                                        .and(operatingTime.openTime.goe(operatingTime.closeTime))  // 닫는 시간이 다음 날인 경우
                                        .and(operatingTime.openTime.loe(requestLocalTime)
                                                .or(operatingTime.closeTime.goe(requestLocalTime)))) // 현재 시간이 Open 이후 또는 Close 이전
                                .then(true)

                                // 3️⃣ **"전날 운영 시간" 고려 (예: 새벽 1시는 전날 운영)**
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.dayOfWeek.eq(previousDay)) // 전날 운영 시간 체크
                                        .and(operatingTime.openTime.goe(operatingTime.closeTime))  // 하루 넘어가는 운영 시간
                                        .and(operatingTime.closeTime.goe(requestLocalTime)))  // 현재 시간이 closeTime보다 이른 경우
                                .then(true)

                                .otherwise(false)
                )
                .from(club)
                .leftJoin(operatingTime).on(
                        club.id.eq(operatingTime.club.id)
                                .and(operatingTime.dayOfWeek.in(requestDay, previousDay)) // 현재 요일 + 전날까지 고려
                )
                // 필터 적용
                .where(
                        (genres != null && !genres.isEmpty()) ? club.genres.any().id.in(genres) : null,
                        (regions != null && !regions.isEmpty()) ? club.region.id.in(regions) : null,
                        (priceStart != null) ? club.price.goe(priceStart) : null,
                        (priceEnd != null) ? club.price.loe(priceEnd) : null,
                        (isOpen != null && isOpen) ? new CaseBuilder()
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.openTime.loe(requestLocalTime))
                                        .and(operatingTime.closeTime.goe(requestLocalTime)))
                                .then(true)
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.openTime.goe(operatingTime.closeTime))
                                        .and(operatingTime.openTime.loe(requestLocalTime).or(operatingTime.closeTime.goe(requestLocalTime))))
                                .then(true)
                                .when(operatingTime.isNotNull()
                                        .and(operatingTime.dayOfWeek.eq(previousDay))
                                        .and(operatingTime.openTime.goe(operatingTime.closeTime))
                                        .and(operatingTime.closeTime.goe(requestLocalTime)))
                                .then(true)
                                .otherwise(false)
                                .eq(true) : null
                )
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return results.stream()
                .map(tuple -> clubMapper.toResponse(
                        tuple.get(club),
                        tuple.get(1, Boolean.class)
                ))
                .toList();
    }
}
